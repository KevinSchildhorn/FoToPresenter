package com.kevinschildhorn.fotopresenter

import com.kevinschildhorn.fotopresenter.extension.addPath
import com.kevinschildhorn.fotopresenter.extension.navigateBackToPathAtIndex
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

    @Test
    fun ` Navigate Back`() {
        var path = ""
        path = path.addPath("Public")
        path = path.addPath("Subfolder")
        path = path.addPath("SuperSubfolder")
        assertEquals("Public\\Subfolder\\SuperSubfolder", path)
        path = path.navigateBackToPathAtIndex(1)
        assertEquals("Public\\Subfolder", path)

        path = path.addPath("Public")
        path = path.addPath("Subfolder")
        path = path.addPath("SuperSubfolder")
        assertEquals("Public\\Subfolder\\Public\\Subfolder\\SuperSubfolder", path)
        path = path.navigateBackToPathAtIndex(3)
        assertEquals("Public\\Subfolder\\Public\\Subfolder", path)

        path = path.navigateBackToPathAtIndex(0)
        assertEquals("Public", path)

        path = path.addPath("Public")
        path = path.addPath("Subfolder")
        path = path.addPath("SuperSubfolder")
        assertEquals("Public\\Public\\Subfolder\\SuperSubfolder", path)

        path = path.navigateBackToPathAtIndex(-1)
        assertEquals("", path)
    }
}
