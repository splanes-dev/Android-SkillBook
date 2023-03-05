package com.splanes.apps.skillbook.data.common.firebase

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import com.splanes.apps.skillbook.domain.common.error.ConfigFileNotFound
import java.io.InputStreamReader
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseStorageHelper @Inject constructor() {

    suspend inline fun <reified T> downloadJson(path: String, file: String): T? =
        withContext(Dispatchers.IO) {
            runCatching {
                val stream = suspendCoroutine { continuation ->
                    Firebase.storage
                        .reference.child("$path/$file")
                        .stream
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                continuation.resume(task.result.stream)
                            } else {
                                val msg = task.exception?.message
                                continuation.resumeWithException(ConfigFileNotFound(file, msg))
                            }
                        }
                }
                val result = Gson().fromJson(InputStreamReader(stream), T::class.java)
                result
            }.getOrNull()
        }

    suspend fun urlOf(path: String, file: String): String =
        withContext(Dispatchers.IO) {
            runCatching {
                suspendCoroutine { continuation ->
                    Firebase.storage
                        .reference.child("$path/$file")
                        .downloadUrl
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                continuation.resume(task.result.toString())
                            } else {
                                continuation.resume("")
                            }
                        }
                }
            }
        }.getOrNull().orEmpty()
}
