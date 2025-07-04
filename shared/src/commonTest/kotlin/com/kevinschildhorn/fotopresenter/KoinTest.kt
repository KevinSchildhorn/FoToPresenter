package com.kevinschildhorn.fotopresenter

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.kermitLoggerModule
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.image.CachedImageDataSource
import com.kevinschildhorn.fotopresenter.data.network.MockMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import org.koin.dsl.module

private val baseLogger = Logger.withTag("Test")

fun testingModule(settings: MapSettings = MapSettings()) =
    kermitLoggerModule(Logger) + commonModule +
        module {
            single<NetworkHandler> { MockNetworkHandler }
            single<Settings> { settings }

            // TODO: Temp, Logger causes issues for some reason
            single { CachedImageDataSource(get(), null) }
            single<ImageMetadataDataSource> { MockMetadataDataSource() }
            single<SqlDriver> {
                val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
                PlaylistDatabase.Schema.create(driver)
                driver
            }
        }
