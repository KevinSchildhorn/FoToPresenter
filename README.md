# FoToPresenter

![fotopresenter](/images/fotopresenter.png)

#### FoToPresenter is an Android and Desktop application that allows users to present and manage images located on their local NAS (Network Access Storage) device. The application provides a modern, responsive interface for image presentation and management while using the SMB (Server Message Block) protocol to communicate to an IP address. Install it on your tablet as a digital picture frame or browse your photos from your computer.

> FotoPresenter is my hobbyist project, it's not intended to be a fully fledged professional app. I enjoy programming and I have a NAS which I wanted to use for my photos. I figured it might be something others might enjoy as well.

## Features

* Create a slideshow of your favorite photos from your NAS
* Browse your photos with FotoPresenters photo explorer
* Find the right photo with various searching tools
* Start a slideshow directly from a folder, or create a custom playlist

***

### Browse your photos

<p align="center">
<img src="/images/directory.png" width="500" /> 
</p>

Navigate through all of your directories, as FotoPresenter shows your "png","jpg","jpeg", and "bmp" files. Pop in and out of folders as you would expect in a typical file explorer.

#### Previews 

<p align="center">
<img src="/images/directory_preview.png" width="500"  /> 
</p>

Preview your images by tapping on them to see a larger version of your photo. This supports navigating forward and back with the arrow buttons in the corners.

#### Navigation

<p align="center">
<img src="/images/navigation_bar.png" /> 
</p>

Navigate back through your folders by tapping on which folder you'd like to return to.

#### Settings

There are many options for finding the picture you want, which can be located at the top of the screen.

<p align="center">
<img src="/images/topbar.png" /> 
</p>

1. **Resize** - Changes the amount of images per row of the grid
2. **Search** for images/folders by name that in the current folder
3. **Tag Search** - Search for images based on EXIF metadata tags
4. **Sort** - Sort the current folder based on name or date
5. **More** - More options for your current folder, such as starting a slideshow

#### Advanced Search

<p align="center">
<img src="/images/metadata_search.png" width="500" /> 
</p>

For more advanced searching you can use search images based on the date or EXIF tags. This supports multiple tags and searching sub-folders so you can deep dive into folders (i.e. looking for "dog" photos in 2016/vacations/summer/beach).

#### Creating a slideshow from a folder

<p align="center">
<img src="/images/slideshow_settings.png" width="500" /> 
</p>

You can easily start a slideshow from a folder on your NAS, by long pressing on a folder and choosing to start a slideshow. From there you can choose how many folders to include and extensive options for shuffling your photos.

### Creating a playlist

<p align="center">
<img src="/images/playlists.gif" width="500" /> 
</p>

Not only can you start a slideshow from a folder, but you can add playlists to create a custom slideshow of your photos. Individual photos can be added to playlists, as well as folders which will dynamically be added (meaning any photos in the folder when the slideshow starts will be added automatically).

## Development

If you are at all interested in the development of FoToPresenter then take a look at the [Dev ReadMe](DEVREADME.md) where I cover more specifics into the project and the development process.
