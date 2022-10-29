package me.kevinschildhorn.common.ui.atomic.designproperty

data class MarginDesignProperty(
    val margin: Int = 0,
    val marginX: Int = margin,
    val marginY: Int = margin
) : DesignProperty()