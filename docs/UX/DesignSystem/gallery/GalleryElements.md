# Gallery Elements

## Gallery Item
![GalleryItem](/photos/GalleryItem.png)

Gallery Item is an atom
* Is a View
* 1:1 ratio
* 5pt radius
* 5pt margin (x and y)
* Width is generated based on screen width, with a max size of (100pt)
* by default on portrait phones there are four Gallery Items per row

## Gallery Photo

Gallery Item is a molecule
* Contains Gallery Item, and it's properties
* Contains Image fully in Gallery Item View

## Gallery Folder

![Folder](/photos/Folder.png)

Gallery Folder is a molecule

* Contains Gallery Item, and it's properties
* Also contains Label atom (surface)
* Contains up to four Images above the Label, depending on sub-directory
* These Images are evenly spaced in the space above the label
