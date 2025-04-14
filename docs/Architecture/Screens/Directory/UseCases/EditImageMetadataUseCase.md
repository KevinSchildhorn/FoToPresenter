# Edit Image Metadata Use Case

## Overview
The edit image metadata use case handles the reading and writing of image metadata, particularly tags and comments, for images in the directory view. It involves multiple components working together to provide a seamless metadata editing experience.

## Code Flow

### 1. User Interaction (DirectoryScreen)
- User can access metadata editing through:
  - Long-pressing an image in the `DirectoryGrid`
  - Selecting "Set Metadata" from the action sheet
- This triggers:
  - `DirectoryViewModel.startEditingMetadata()`
  - Shows metadata editing overlay
  - Displays current metadata if available

### 2. ViewModel Processing (DirectoryViewModel)
- When `startEditingMetadata()` is called:
  - Retrieves current directory from overlay state
  - Calls `dataSource.readMetadataFromFile()` with the image path
  - Updates UI state to show metadata editing overlay
- When `saveMetadata()` is called:
  - Gets current directory from overlay state
  - Calls `dataSource.writeMetadataToFile()` with new metadata
  - Clears overlay on completion

### 3. Metadata Management (ImageMetadataDataSource)
- The `ImageMetadataDataSource` handles:
  - Reading EXIF metadata from images
  - Writing metadata back to images
  - Managing metadata format conversion
- Key functions:
  - `readMetadataFromFile()`: Extracts metadata from image
  - `writeMetadataToFile()`: Updates image metadata
  - Uses Kim library for EXIF handling

### 4. Data Structures
- `MetadataFileDetails`:
  - Stores file path and tags
  - Provides tag string conversion
  - Used for metadata persistence
- `MetadataDetails`:
  - Contains list of file metadata
  - Used for bulk metadata operations
  - Supports serialization

### 5. State Management
- Metadata state is managed through:
  - `DirectoryOverlayUiState.Actions.EditMetaData`
  - `MetadataFileDetails` for current metadata
  - UI state updates in ViewModel

## State Transitions
```
NONE (initial)
  → EDIT_METADATA (when editing starts)
  → NONE (when editing completes or is cancelled)
```

## Error Handling
- File access errors
- Invalid metadata format
- Write permission issues
- Network connectivity problems
- Error states are displayed to user
- Editing can be retried after errors

## Metadata Features
- Tag management
- EXIF data reading
- Metadata persistence
- Bulk metadata operations
- Format conversion support