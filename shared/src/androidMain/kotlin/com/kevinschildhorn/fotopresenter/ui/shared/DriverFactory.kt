package com.kevinschildhorn.fotopresenter.ui.shared

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.kevinschildhorn.fotopresenter.PlaylistDatabase

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(PlaylistDatabase.Schema, context, "playlist.db")
    }
}
