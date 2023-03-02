package com.splanes.apps.skillbook.ui.feature.workhistory.model

import com.splanes.apps.skillbook.ui.R

sealed interface WorkSearchStateVisuals {
    val name: Int
    val description: Int

    object CloseToChange : WorkSearchStateVisuals {
        override val name: Int = R.string.work_status_close_change_name
        override val description: Int = R.string.work_status_close_change_description
    }

    object OpenToOffers : WorkSearchStateVisuals {
        override val name: Int = R.string.work_status_open_offers_name
        override val description: Int = R.string.work_status_open_offers_description
    }

    object OpenToChange : WorkSearchStateVisuals {
        override val name: Int = R.string.work_status_open_change_name
        override val description: Int = R.string.work_status_open_change_description
    }
}
