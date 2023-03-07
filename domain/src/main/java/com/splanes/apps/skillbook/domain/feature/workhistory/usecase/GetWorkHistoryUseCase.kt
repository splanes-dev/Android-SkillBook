package com.splanes.apps.skillbook.domain.feature.workhistory.usecase

import com.splanes.apps.skillbook.domain.common.base.UseCase
import com.splanes.apps.skillbook.domain.feature.workhistory.model.WorkHistoryData
import com.splanes.apps.skillbook.domain.feature.workhistory.repository.WorkHistoryRepository
import javax.inject.Inject

class GetWorkHistoryUseCase @Inject constructor(
    private val repository: WorkHistoryRepository
) : UseCase<Unit, WorkHistoryData>() {

    override suspend fun execute(params: Unit): WorkHistoryData =
        repository.getWorkHistory()
}
