package com.kevinschildhorn.atomik.color.base

/**
 * A wrapper for an AtomikColor associated with an id.
 *
 * @property id the id associated with the color
 * @property color the color
 */
@Suppress("SpellCheckingInspection")
public interface AtomikColorType {
    public val id: String
    public val color: AtomikColor
}
