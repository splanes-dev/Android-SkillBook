package com.splanes.apps.skillbook.domain.feature.profile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ProfileCurrentStatusData : Parcelable {
    @Parcelize
    object CloseToChange : ProfileCurrentStatusData()

    @Parcelize
    object OpenToChange : ProfileCurrentStatusData()

    @Parcelize
    object OpenToOffers : ProfileCurrentStatusData()
}
