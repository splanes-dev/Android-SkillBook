package com.splanes.apps.skillbook.ui.feature.profile.model

import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkSearchStateVisuals
import com.splanes.apps.skillbook.ui.feature.workhistory.model.WorkVisuals

data class ProfileCurrentStatusVisuals(
    val workSearchState: WorkSearchStateVisuals = WorkSearchStateVisuals.OpenToOffers,
    val currentWork: WorkVisuals = WorkVisuals()
)
