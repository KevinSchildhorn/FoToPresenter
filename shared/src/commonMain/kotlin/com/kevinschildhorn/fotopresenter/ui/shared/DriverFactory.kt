package com.kevinschildhorn.fotopresenter.ui.shared

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}
