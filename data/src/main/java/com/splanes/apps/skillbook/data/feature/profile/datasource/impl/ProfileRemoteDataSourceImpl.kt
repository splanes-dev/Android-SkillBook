package com.splanes.apps.skillbook.data.feature.profile.datasource.impl

import com.splanes.apps.skillbook.data.common.firebase.FirebaseStorageHelper
import com.splanes.apps.skillbook.data.feature.profile.datasource.ProfileRemoteDataSource
import com.splanes.apps.skillbook.data.feature.profile.dto.ProfileDto
import com.splanes.apps.skillbook.domain.common.error.ConfigFileNotFound
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val firebaseHelper: FirebaseStorageHelper
) : ProfileRemoteDataSource {

    override suspend fun getProfile(): ProfileDto {
        val profile = firebaseHelper.downloadJson<ProfileDto>(
            path = ProfilePath,
            file = ProfileJson
        )?.let { profile ->
            profile.copy(
                contact = profile.contact.copy(
                    imageUrl = firebaseHelper.urlOf(
                        path = ProfilePath,
                        file = profile.contact.imageUrl.orEmpty()
                    )
                ),
                currentStatus = profile.currentStatus.copy(
                    logoUrl = firebaseHelper.urlOf(
                        path = LogoPath,
                        file = profile.currentStatus.logoUrl.orEmpty()
                    )
                )
            )
        }

        return profile ?: throw ConfigFileNotFound(ProfileJson)
    }
}

private const val LogoPath = "logo"
private const val ProfilePath = "profile"
private const val ProfileJson = "profile.json"
