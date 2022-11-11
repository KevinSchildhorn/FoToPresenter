package me.kevinschildhorn.common.layers.data.repositories

import me.kevinschildhorn.common.layers.data.datasources.DirectoriesDataSource

/**
Fetches Directory Information from a [DirectoriesDataSource]
 **/
class DirectoriesRepository(
    private val dataSource: DirectoriesDataSource
) {

    suspend fun fetchDirectories() {
    }
}
