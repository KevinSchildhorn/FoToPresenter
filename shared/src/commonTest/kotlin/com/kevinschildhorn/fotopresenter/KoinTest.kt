package com.kevinschildhorn.fotopresenter

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import org.koin.dsl.module

private val baseLogger = Logger.withTag("Test")

fun testingModule(settings: MapSettings = MapSettings()) =
    module {
        single<NetworkHandler> { MockNetworkHandler }
        single<Settings> { settings }

        single<SqlDriver> {
            val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            PlaylistDatabase.Schema.create(driver)
            driver
        }
    } + commonModule
