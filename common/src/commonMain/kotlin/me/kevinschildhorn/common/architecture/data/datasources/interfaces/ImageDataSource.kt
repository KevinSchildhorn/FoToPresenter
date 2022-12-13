package me.kevinschildhorn.common.architecture.data.datasources.interfaces

typealias Images = List<Any>

/**
An interface for Data Sources that contain [Images]
 **/
interface ImageDataSource {
    fun fetchImages(): Images
}
