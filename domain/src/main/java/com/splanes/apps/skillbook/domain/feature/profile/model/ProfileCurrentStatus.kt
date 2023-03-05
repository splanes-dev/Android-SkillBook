package com.splanes.apps.skillbook.domain.feature.profile.model

enum class ProfileCurrentStatus(val value: String) {
    CloseToChange(value = "Close"),
    OpenToChange(value = "Open"),
    OpenToOffers(value = "Offers")
}
