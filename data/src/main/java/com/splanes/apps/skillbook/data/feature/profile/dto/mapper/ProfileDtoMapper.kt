package com.splanes.apps.skillbook.data.feature.profile.dto.mapper

import com.splanes.apps.skillbook.data.feature.profile.dto.ProfileDto
import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileAboutMeData
import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileContactData
import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileCurrentStatus
import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileCurrentStatusData
import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileData
import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileLocation
import com.splanes.apps.skillbook.domain.feature.workhistory.model.WorkHistoryEntryData
import javax.inject.Inject

class ProfileDtoMapper @Inject constructor() {

    fun map(dto: ProfileDto): ProfileData =
        ProfileData(
            aboutMe = ProfileAboutMeData(description = dto.aboutMe.description.orEmpty()),
            contact = ProfileContactData(
                name = dto.contact.name.orEmpty(),
                location = ProfileLocation(
                    description = dto.contact.location?.description.orEmpty(),
                    coordinates = dto.contact.location?.coordinates.orEmpty()
                ),
                email = dto.contact.email.orEmpty(),
                phone = dto.contact.phone.orEmpty(),
                profileImageUrl = dto.contact.imageUrl.orEmpty(),
                linkedInUrl = dto.contact.linkedInUrl.orEmpty(),
                githubUrl = dto.contact.githubUrl.orEmpty()
            ),
            currentStatus = ProfileCurrentStatusData(
                work = WorkHistoryEntryData(
                    enterprise = dto.currentStatus.enterprise.orEmpty(),
                    charge = dto.currentStatus.charge.orEmpty(),
                    description = dto.currentStatus.description.orEmpty(),
                    iconUrl = dto.currentStatus.logoUrl.orEmpty(),
                    startDate = dto.currentStatus.startDate.orEmpty(),
                    endDate = dto.currentStatus.endDate.orEmpty()
                ),
                status = ProfileCurrentStatus.values().first { status ->
                    status.value == dto.currentStatus.status
                }
            )
        )
}
