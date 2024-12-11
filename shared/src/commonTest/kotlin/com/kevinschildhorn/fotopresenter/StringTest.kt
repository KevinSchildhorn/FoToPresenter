package com.kevinschildhorn.fotopresenter

import com.kevinschildhorn.fotopresenter.data.Path
import kotlin.test.Test
import kotlin.test.assertEquals

/**
Testing [String]
 **/
class StringTest {
    @Test
    fun `Add Path`() {
        var path = Path.EMPTY
        path = path.addPath("Public")
        assertEquals("Public", path.toString())
        path = path.addPath("Subfolder")
        assertEquals("Public\\Subfolder", path.toString())
        path = path.addPath("")
        assertEquals("Public", path.toString())
    }

    @Test
    fun ` Navigate Back`() {
        var path = Path.EMPTY
        path = path.addPath("Public")
        path = path.addPath("Subfolder")
        path = path.addPath("SuperSubfolder")
        assertEquals("Public\\Subfolder\\SuperSubfolder", path.toString())
        path = path.navigateBackToPathAtIndex(1)
        assertEquals("Public\\Subfolder", path.toString())

        path = path.addPath("Public")
        path = path.addPath("Subfolder")
        path = path.addPath("SuperSubfolder")
        assertEquals("Public\\Subfolder\\Public\\Subfolder\\SuperSubfolder", path.toString())
        path = path.navigateBackToPathAtIndex(3)
        assertEquals("Public\\Subfolder\\Public\\Subfolder", path.toString())

        path = path.navigateBackToPathAtIndex(0)
        assertEquals("Public", path.toString())

        path = path.addPath("Public")
        path = path.addPath("Subfolder")
        path = path.addPath("SuperSubfolder")
        assertEquals("Public\\Public\\Subfolder\\SuperSubfolder", path.toString())

        path = path.navigateBackToPathAtIndex(-1)
        assertEquals("", path.toString())
    }
}
