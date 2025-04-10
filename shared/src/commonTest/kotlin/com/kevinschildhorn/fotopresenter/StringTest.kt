package com.kevinschildhorn.fotopresenter

import com.kevinschildhorn.fotopresenter.data.Path
import kotlin.test.Test
import kotlin.test.assertEquals

/**
Testing [String]
 **/
class StringTest {
    @Test
    fun `Add_Path`() {
        var path = Path.EMPTY
        path = path.addPath("Public")
        assertEquals(expected = "Public", actual = path.toString())
        path = path.addPath("Subfolder")
        assertEquals(expected = "Public\\Subfolder", actual = path.toString())
        path = path.addPath("")
        assertEquals(expected = "Public", actual = path.toString())
    }

    @Test
    fun `Navigate_Back`() {
        var path = Path.EMPTY
        path = path.addPath("Public")
        path = path.addPath("Subfolder")
        path = path.addPath("SuperSubfolder")
        assertEquals(expected = "Public\\Subfolder\\SuperSubfolder", actual = path.toString())
        path = path.navigateBackToPathAtIndex(1)
        assertEquals(expected = "Public\\Subfolder", actual = path.toString())

        path = path.addPath("Public")
        path = path.addPath("Subfolder")
        path = path.addPath("SuperSubfolder")
        assertEquals(
            expected = "Public\\Subfolder\\Public\\Subfolder\\SuperSubfolder",
            actual = path.toString()
        )
        path = path.navigateBackToPathAtIndex(3)
        assertEquals(expected = "Public\\Subfolder\\Public\\Subfolder", actual = path.toString())

        path = path.navigateBackToPathAtIndex(0)
        assertEquals(expected = "Public", actual = path.toString())

        path = path.addPath("Public")
        path = path.addPath("Subfolder")
        path = path.addPath("SuperSubfolder")
        assertEquals(
            expected = "Public\\Public\\Subfolder\\SuperSubfolder",
            actual = path.toString()
        )

        path = path.navigateBackToPathAtIndex(-1)
        assertEquals(expected = "/", actual = path.toString())
    }
}
