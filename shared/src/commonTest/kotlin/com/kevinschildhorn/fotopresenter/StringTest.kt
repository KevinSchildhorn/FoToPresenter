package com.kevinschildhorn.fotopresenter

import com.kevinschildhorn.fotopresenter.extension.addPath
import kotlin.test.Test
import kotlin.test.assertEquals

/**
Testing [String]
 **/
class StringTest {
    @Test
    fun `Add Path`() {
        var path = ""
        path = path.addPath("Public")
        assertEquals("Public", path)
        path = path.addPath("Subfolder")
        assertEquals("Public\\Subfolder", path)
        path = path.addPath("")
        assertEquals("Public", path)
    }
}
