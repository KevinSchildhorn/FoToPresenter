package me.kevinschildhorn.common.architecture.data.repositories

/**
Fetches [Images] and Directories to display in the Gallery Screen
 **/
class GalleryRepository(
    private val directoriesRepository: DirectoriesRepository,
    private val imagesRepository: ImagesRepository,
)
