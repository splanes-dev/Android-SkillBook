package com.splanes.apps.skillbook.ui.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun <T> rememberStateOf(value: T): MutableState<T> = remember { mutableStateOf(value) }
