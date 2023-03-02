package com.splanes.apps.skillbook.ui.components.error

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.components.error.model.ErrorDialogVisuals
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@Composable
fun ErrorDialog(state: ErrorDialogState) {
    val scope = rememberCoroutineScope()
    AnimatedVisibility(
        visible = state.visible,
        enter = expandIn(animationSpec = tween(durationMillis = 300)),
        exit = shrinkOut(animationSpec = tween(durationMillis = 300))
    ) {
        state.dialogVisuals?.let { visuals ->
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.anim_generic_error)
            )
            val animationState by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            AlertDialog(
                icon = {
                    Icon(
                        imageVector = visuals.icon,
                        contentDescription = visuals.title,
                        tint = MaterialTheme.colorScheme.error.copy(alpha = .3f)
                    )
                },
                title = {
                    Text(
                        text = visuals.title,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.error.copy(alpha = .5f)
                    )
                },
                text = {
                    Column {
                        LottieAnimation(
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.CenterHorizontally),
                            composition = composition,
                            progress = { animationState }
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = visuals.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.Justify
                        )
                    }
                },
                onDismissRequest = { scope.launch { state.hide() } },
                confirmButton = {
                    Button(onClick = { scope.launch { state.hide() } }) {
                        Text(text = visuals.closeButton)
                    }
                },
                shape = RoundedCornerShape(20.dp)
            )
        }
    }
}

class ErrorDialogState {
    var dialogVisuals: ErrorDialogVisuals? by mutableStateOf(null)
    val visible get() = dialogVisuals != null

    private val mutex = Mutex()

    suspend fun show(visuals: ErrorDialogVisuals) = mutex.withLock {
        dialogVisuals = visuals
    }

    suspend fun hide() = mutex.withLock {
        dialogVisuals = null
    }
}

@Composable
fun rememberErrorDialogState() = remember { ErrorDialogState() }

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
private fun ErrorDialogPreview() {
    SkillBookTheme {
        val scope = rememberCoroutineScope()
        val state = rememberErrorDialogState()
        val dialogVisuals = ErrorDialogVisuals(
            title = "Generic error",
            description = "Seems that something went wrong, please try again in a few minutes.",
            closeButton = "Oki Doki"
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { scope.launch { state.show(dialogVisuals) } }) {
                Text("Show dialog")
            }
        }
        ErrorDialog(state = state)
    }
}
