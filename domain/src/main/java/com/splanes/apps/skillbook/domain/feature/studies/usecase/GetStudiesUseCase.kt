package com.splanes.apps.skillbook.domain.feature.studies.usecase

import com.splanes.apps.skillbook.domain.common.base.UseCase
import com.splanes.apps.skillbook.domain.feature.studies.model.StudiesData
import com.splanes.apps.skillbook.domain.feature.studies.repository.StudiesRepository
import javax.inject.Inject

class GetStudiesUseCase @Inject constructor(
    val repository: StudiesRepository
) : UseCase<Unit, StudiesData>() {

    override suspend fun execute(params: Unit): StudiesData =
        repository.getStudies()
}
