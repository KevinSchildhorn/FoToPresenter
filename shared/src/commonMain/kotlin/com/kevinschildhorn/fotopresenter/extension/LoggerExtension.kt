package com.kevinschildhorn.fotopresenter.extension

import co.touchlab.kermit.Logger

fun Logger.logLargeTitle(string: String) {
    this.i { "" }
    this.i { "" }
    this.i { "-----------------------------------------------------------------------------" }
    this.i { "-------------------------------$string-------------------------------" }
    this.i { "-----------------------------------------------------------------------------" }
    this.i { "" }
}

const val LOGGER_TAG_SUFFIX = "_KS"
