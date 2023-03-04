package com.splanes.apps.skillbook.ui.feature.profile.components

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.common.utils.ImageResource
import com.splanes.apps.skillbook.ui.common.utils.rememberStateOf
import com.splanes.apps.skillbook.ui.components.expandable.ExpandCollapseArrow
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileContactVisuals
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContact(
    visuals: ProfileContactVisuals,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val containerAlpha by animateFloatAsState(
        targetValue = if (expanded) .5f else .2f,
        label = "container alpha"
    )
    val items = visuals.mapToProfileItems(LocalContext.current)

    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = containerAlpha),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            ProfileHeader(
                expanded = expanded,
                name = visuals.name
            )

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(
                    animationSpec = tween(durationMillis = 750),
                    expandFrom = Alignment.Top
                ),
                exit = shrinkVertically(
                    animationSpec = tween(durationMillis = 750),
                    shrinkTowards = Alignment.Top
                )
            ) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))

                    items.forEach { item ->
                        TextButton(onClick = item.onClick) {
                            when (item.icon) {
                                is ImageResource.Assets -> {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        painter = painterResource(id = item.icon.res),
                                        contentDescription = item.iconContentDescription,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }

                                is ImageResource.Vector -> {
                                    Icon(
                                        imageVector = item.icon.imageVector,
                                        contentDescription = item.iconContentDescription,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.wrapContentSize(),
        shape = CircleShape
    ) {
        Image(
            modifier = Modifier.size(125.dp),
            painter = painterResource(id = R.drawable.cv_photo),
            contentDescription = stringResource(
                id = R.string.cv_photo_content_description
            ),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun ProfileName(name: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = name,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ProfileHeader(
    expanded: Boolean,
    name: String
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        AnimatedContent(targetState = expanded, label = "anim profile header") { isExpanded ->
            if (isExpanded) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(end = 36.dp)
                ) {
                    ProfileImage(modifier = Modifier.align(Alignment.CenterHorizontally))

                    Spacer(modifier = Modifier.height(16.dp))

                    ProfileName(name = name)
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 36.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProfileImage()

                    Spacer(modifier = Modifier.width(16.dp))

                    ProfileName(name = name)
                }
            }
        }

        ExpandCollapseArrow(modifier = Modifier.align(Alignment.TopEnd), expanded = expanded)
    }
}

data class ProfileContactItem(
    val name: String,
    val iconContentDescription: String,
    val icon: ImageResource,
    val onClick: () -> Unit
)

private fun ProfileContactVisuals.mapToProfileItems(context: Context) = listOf(
    ProfileContactItem(
        name = location,
        iconContentDescription = context.getString(R.string.location_description),
        icon = ImageResource.Vector(Icons.Rounded.LocationOn),
        onClick = {
            context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("geo:$locationLatLng")).apply {
                    `package` = "com.google.android.apps.maps"
                }
            )
        }
    ),
    ProfileContactItem(
        name = phone,
        iconContentDescription = context.getString(R.string.phone_description),
        icon = ImageResource.Vector(Icons.Rounded.Phone),
        onClick = {
            context.startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone")))
        }
    ),
    ProfileContactItem(
        name = email,
        iconContentDescription = context.getString(R.string.mail_description),
        icon = ImageResource.Vector(Icons.Rounded.MailOutline),
        onClick = {
            context.startActivity(
                Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:$email")
                    putExtra(
                        Intent.EXTRA_SUBJECT,
                        context.getString(R.string.profile_contact_mail_subject)
                    )
                }
            )
        }
    ),
    ProfileContactItem(
        name = context.getString(R.string.github),
        iconContentDescription = context.getString(R.string.github),
        icon = ImageResource.Assets(R.drawable.ic_github),
        onClick = {
            context.startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(githubUrl)
                }
            )
        }
    ),
    ProfileContactItem(
        name = context.getString(R.string.linkedin),
        iconContentDescription = context.getString(R.string.linkedin),
        icon = ImageResource.Assets(R.drawable.ic_linkedin),
        onClick = {
            context.startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(linkedInUrl)
                }
            )
        }
    )
)

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
private fun ProfileContactPreview() {
    SkillBookTheme {
        var expanded by rememberStateOf(value = true)
        ProfileContact(
            visuals = ProfileContactVisuals(
                location = "Barcelona, Spain",
                locationLatLng = "41.4065534,2.166795,13z",
                email = "splanes@protonmail.com",
                phone = "628131871",
                linkedInUrl = "https://www.linkedin.com/in/sergi-planes-tor-8318a998/",
                githubUrl = "https://www.github.com/splanes-dev"
            ),
            expanded = expanded,
            onClick = { expanded = !expanded }
        )
    }
}
