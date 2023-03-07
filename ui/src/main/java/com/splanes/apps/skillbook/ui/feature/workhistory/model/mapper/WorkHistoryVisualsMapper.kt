package com.splanes.apps.skillbook.ui.feature.workhistory.model.mapper

import com.splanes.apps.skillbook.domain.feature.workhistory.model.WorkHistoryData
import com.splanes.apps.skillbook.domain.feature.workhistory.model.WorkHistoryEntryData
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkVisuals
import javax.inject.Inject

class WorkHistoryVisualsMapper @Inject constructor() {

    fun map(history: WorkHistoryData): List<WorkVisuals> =
        history.entries.map(::map)

    private fun map(entry: WorkHistoryEntryData): WorkVisuals =
        WorkVisuals(
            iconUrl = entry.iconUrl,
            enterprise = entry.enterprise,
            charge = entry.charge,
            description = entry.description,
            startDate = entry.startDate,
            endDate = entry.endDate.ifBlank { null }
        )
}
