package me.kevinschildhorn.common.layers.data.repositories

import me.kevinschildhorn.common.layers.data.datasources.CredentialsDataSource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CredentialsRepository : KoinComponent {
    val dataSource: CredentialsDataSource by inject()


}