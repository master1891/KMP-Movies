package com.nels.master.kmptutorial1.data


interface RegionDatasource {
    suspend fun fetchRegion(): String
}


class RegionRepository(
    private val datasource: RegionDatasource
) {
    suspend fun fetchRegion() = datasource.fetchRegion()
}

