package com.splanes.apps.skillbook.ui.feature.profile.model.mapper

import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileAboutMeData
import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileContactData
import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileCurrentStatus
import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileCurrentStatusData
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileAboutMeVisuals
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileContactVisuals
import com.splanes.apps.skillbook.ui.feature.profile.model.ProfileCurrentStatusVisuals
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkSearchStateVisuals
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkVisuals
import javax.inject.Inject

class ProfileVisualsMapper @Inject constructor() {

    fun map(contact: ProfileContactData): ProfileContactVisuals =
        ProfileContactVisuals(
            name = contact.name,
            imageUrl = contact.profileImageUrl,
            location = contact.location.description,
            locationLatLng = contact.location.coordinates,
            email = contact.email,
            phone = contact.phone,
            linkedInUrl = contact.linkedInUrl,
            githubUrl = contact.githubUrl
        )

    fun map(aboutMe: ProfileAboutMeData): ProfileAboutMeVisuals =
        ProfileAboutMeVisuals(description = aboutMe.description)

    fun map(currentStatus: ProfileCurrentStatusData): ProfileCurrentStatusVisuals =
        ProfileCurrentStatusVisuals(
            workSearchState = when (currentStatus.status) {
                ProfileCurrentStatus.CloseToChange -> WorkSearchStateVisuals.CloseToChange
                ProfileCurrentStatus.OpenToChange -> WorkSearchStateVisuals.OpenToChange
                ProfileCurrentStatus.OpenToOffers -> WorkSearchStateVisuals.OpenToOffers
            },
            currentWork = WorkVisuals(
                iconUrl = currentStatus.work.iconUrl,
                enterprise = currentStatus.work.enterprise,
                charge = currentStatus.work.charge,
                description = currentStatus.work.description,
                startDate = currentStatus.work.startDate,
                endDate = currentStatus.work.endDate.ifBlank { null }
            )
        )
}
