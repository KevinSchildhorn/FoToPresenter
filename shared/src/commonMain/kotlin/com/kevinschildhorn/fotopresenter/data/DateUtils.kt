package com.kevinschildhorn.fotopresenter.data

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

val dropdownMonths = listOf(
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
)

val dropdownYears: List<Int>
    get() {
        val currentYear: Int = Clock.System.todayIn(TimeZone.currentSystemDefault()).year
        return ((currentYear - 99)..currentYear).toList().reversed()
    }