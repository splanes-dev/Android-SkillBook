package com.splanes.apps.skillbook.ui.feature.profile.components

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splanes.apps.skillbook.ui.R
import com.splanes.apps.skillbook.ui.common.utils.rememberStateOf
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileContactVisuals
import com.splanes.apps.skillbook.ui.theme.SkillBookTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContact(
    visuals: ProfileContactVisuals,
    modifier: Modifier = Modifier
) {
    var expanded by rememberStateOf(true)
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 0f else 180f,
        label = "icon rotation"
    )
    val items = visuals.mapToProfileItems(LocalContext.current)

    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = .33f),
        onClick = { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.profile_contact_section_title),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    modifier = Modifier.rotate(rotation),
                    imageVector = Icons.Rounded.ExpandLess,
                    contentDescription = stringResource(
                        id = if (expanded) {
                            R.string.expanded_description
                        } else {
                            R.string.collapsed_description
                        }
                    ),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(animationSpec = spring(), expandFrom = Alignment.Top),
                exit = shrinkVertically(animationSpec = tween(), shrinkTowards = Alignment.Top)
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

data class ProfileContactItem(
    val name: String,
    val iconContentDescription: String,
    val icon: ImageResource,
    val onClick: () -> Unit
)

sealed class ImageResource {
    data class Vector(val imageVector: ImageVector) : ImageResource()
    data class Assets(@DrawableRes val res: Int) : ImageResource()
}

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
        ProfileContact(
            visuals = ProfileContactVisuals(
                location = "Barcelona, Spain",
                locationLatLng = "41.4065534,2.166795,13z",
                email = "splanes@protonmail.com",
                phone = "628131871",
                linkedInUrl = "https://www.linkedin.com/in/sergi-planes-tor-8318a998/",
                githubUrl = "https://www.github.com/splanes-dev"
            )
        )
    }
}
