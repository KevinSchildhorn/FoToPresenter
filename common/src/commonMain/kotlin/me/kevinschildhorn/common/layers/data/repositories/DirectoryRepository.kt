package me.kevinschildhorn.common.layers.data.repositories

import me.kevinschildhorn.common.layers.data.datasources.DirectoryDataSource

class DirectoryRepository {
    val dataSource: DirectoryDataSource = DirectoryDataSource()
}