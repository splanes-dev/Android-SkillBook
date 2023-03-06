package com.splanes.apps.skillbook.data.feature.studies.datasource.impl

import com.splanes.apps.skillbook.data.feature.studies.datasource.StudiesCacheDataSource
import com.splanes.apps.skillbook.data.feature.studies.dto.StudiesDto

object StudiesCache : StudiesCacheDataSource {

    override var studies: StudiesDto? = null
        private set

    override fun save(studies: StudiesDto) {
        this.studies = studies
    }
}
