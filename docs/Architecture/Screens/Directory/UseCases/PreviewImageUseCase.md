# Preview Image Use Case

## Overview
The preview image use case handles the display and navigation of images when a user clicks on an image in the directory view. It involves multiple components working together to provide a smooth image preview experience with navigation capabilities.

## Code Flow

### 1. User Interaction (DirectoryScreen)
- User clicks on an image in the `DirectoryGrid`
- This triggers `onImageDirectoryPressed` callback with the image's ID
- The callback:
  - Clears keyboard focus
  - Calls `DirectoryViewModel.setSelectedImageById(imageId)`
  - Calls `DirectoryViewModel.showOverlay(DirectoryOverlayType.IMAGE)`

### 2. ViewModel Processing (DirectoryViewModel)
- When `setSelectedImageById` is called:
  - Calls `imagePreviewNavigator.setImageIndex()` with the image ID
  - The navigator updates its internal state and emits the new preview state
- The UI state is updated through a combined flow that:
  - Combines directory contents with the image preview state
  - Updates the overlay state to show the image preview
  - Handles state transitions between preview and other overlay types

### 3. Image Preview Navigation (ImagePreviewNavigator)
- The `ImagePreviewNavigator` manages:
  - Current image index
  - List of available images
  - Navigation between images
- Provides functions for:
  - `showPreviousImage()`: Moves to the previous image
  - `showNextImage()`: Moves to the next image
  - `setImageIndex()`: Sets a specific image
  - `clearPresentedImage()`: Clears the preview

### 4. UI Display (ImagePreviewOverlay)
- The `ImagePreviewOverlay` composable:
  - Shows the selected image in a full-screen overlay
  - Provides navigation buttons for previous/next
  - Handles image loading and display
  - Supports dismissal through a tap outside the image

### 5. State Management
- The preview state is managed through:
  - `DirectoryScreenUIState.overlayUiState`
  - `ImagePreviewNavigator.imagePreviewState`
  - Combined flows in the ViewModel ensure consistent state

## State Transitions
```
NONE (initial)
  → IMAGE_PREVIEW (when image is selected)
  → NONE (when preview is dismissed)
```

## Error Handling
- Image loading errors are handled gracefully
- Navigation wraps around at list boundaries
- State is preserved during navigation
- Preview can be dismissed at any time

## Navigation Features
- Circular navigation (wraps around at ends)
- Keyboard shortcuts for navigation
- Touch gestures for mobile
- Click/tap controls for desktop