package com.splanes.apps.skillbook.domain.feature.profile.usecase

import com.splanes.apps.skillbook.domain.common.base.UseCase
import com.splanes.apps.skillbook.domain.feature.profile.model.ProfileData
import com.splanes.apps.skillbook.domain.feature.profile.repository.ProfileRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) : UseCase<Unit, ProfileData>() {

    override suspend fun execute(params: Unit): ProfileData =
        repository.getProfile()
}
