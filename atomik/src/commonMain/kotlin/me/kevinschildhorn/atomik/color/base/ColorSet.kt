package me.kevinschildhorn.atomik.color.base

interface ColorSet {
    val defaultColor: AtomikColor
    fun getColor(name: String): AtomikColor
}
