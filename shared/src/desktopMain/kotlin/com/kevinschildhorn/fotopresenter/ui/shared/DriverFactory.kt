package com.kevinschildhorn.fotopresenter.ui.shared

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.kevinschildhorn.fotopresenter.PlaylistDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        PlaylistDatabase.Schema.create(driver)
        return driver
    }
}
