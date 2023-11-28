package me.kevinschildhorn.common.architecture.data.repositories

import me.kevinschildhorn.common.architecture.data.datasources.DirectoriesDataSource

/**
Fetches Directory Information from a [DirectoriesDataSource]
 **/
class DirectoriesRepository(
    private val dataSource: DirectoriesDataSource
) {

    suspend fun fetchDirectories() {
    }
}
