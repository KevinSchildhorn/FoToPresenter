package me.kevinschildhorn.common.ui.atomic.designproperty

data class SizeDesignProperty(
    val width:Int? = null,
    val height:Int? = null,
    val constrainProportions: Boolean = false,
) : DesignProperty()