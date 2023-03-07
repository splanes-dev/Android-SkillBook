package com.splanes.apps.skillbook.data.feature.workhistory.dto.mapper

import com.splanes.apps.skillbook.data.feature.workhistory.dto.WorkHistoryDto
import com.splanes.apps.skillbook.data.feature.workhistory.dto.WorkHistoryEntryDto
import com.splanes.apps.skillbook.domain.feature.workhistory.model.WorkHistoryData
import com.splanes.apps.skillbook.domain.feature.workhistory.model.WorkHistoryEntryData
import javax.inject.Inject

class WorkHistoryDtoMapper @Inject constructor() {

    fun map(dto: WorkHistoryDto): WorkHistoryData =
        WorkHistoryData(entries = dto.workEntries?.map(::map).orEmpty())

    private fun map(dto: WorkHistoryEntryDto): WorkHistoryEntryData =
        WorkHistoryEntryData(
            enterprise = dto.enterprise.orEmpty(),
            charge = dto.charge.orEmpty(),
            description = dto.description.orEmpty(),
            iconUrl = dto.logoUrl.orEmpty(),
            startDate = dto.startDate.orEmpty(),
            endDate = dto.endDate.orEmpty()
        )
}
