# Directory Screen and ViewModel

While the Directory Screen initially sounds like a straightforward screen, there's actually a lot going on here. This doc will cover what happens and how.

This Screen handles:
* Navigating your FTP Server
* Showing large previews of the images
* TODO: Editing Image Metadata
* TODO: Adding Folders to a playlist
* TODO: Adding Images to a playlist
* TODO: Starting a Slideshow from a Folder

These are handled using short clicks and long clicks on mobile, and left and right clicks on desktop.

## Directory Navigation

This is done using the `DirectoryNavigator`. 
The `DirectoryViewModel` passes commands to the Navigator which then handles these states and contains the current state as a Flow.

This handles clicking on Folders (and the navigation bar), while clicking on images is handled by the next part

## Image Previews

Image previews appear if the user clicks on an image, which is shown in an overlay. This overlay shows an image and supports moving to next and previous image.
The state of the preview is handled by the `ImagePreviewNavigator`. This handles the current image the user is looking at, and handles changes in which image is shown.


## Editing Image Metadata



