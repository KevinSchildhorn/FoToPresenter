# Navigate Directories Use Case

## Overview
The navigate directories use case handles the navigation through the directory structure of the FTP/SMB server. It involves multiple components working together to provide a seamless directory browsing experience with features like breadcrumb navigation, sorting, and searching.

## Code Flow

### 1. User Interaction (DirectoryScreen)
- User can navigate directories through multiple paths:
  - Clicking a folder in the `DirectoryGrid`
  - Using the breadcrumb navigation bar
  - Clicking the home button to return to root
- Each interaction triggers appropriate callbacks:
  - `onFolderPressed`: For grid folder clicks
  - `onHome`: For home button clicks
  - `onItem`: For breadcrumb navigation clicks

### 2. ViewModel Processing (DirectoryViewModel)
- The ViewModel handles navigation through:
  - `navigateIntoDirectory(id: Long)`: For moving into subdirectories
  - `navigateBackToDirectory(index: Int)`: For moving back in the path
  - `refreshScreen()`: For refreshing current directory contents
- Each navigation action:
  - Updates UI state to LOADING
  - Clears search text
  - Calls appropriate DirectoryNavigator methods
  - Handles errors through tryCatch block

### 3. Directory Navigation (DirectoryNavigator)
- The `DirectoryNavigator` manages:
  - Current directory path
  - Directory contents state
  - Navigation history
  - Sorting and filtering
- Key functions:
  - `navigateIntoDirectory(id: Long)`: Moves into a subdirectory
  - `navigateBackToDirectory(index: Int)`: Moves back to a previous directory
  - `refreshDirectoryContents()`: Updates current directory contents
  - `setSortType()`: Changes sort order
  - `setSearch()`: Filters directory contents

### 4. UI Components
- `DirectoryNavigationBar`: Shows breadcrumb navigation
  - Home button for root directory
  - Clickable path segments
  - Visual hierarchy of current location
- `DirectoryGrid`: Displays current directory contents
  - Folders and images in a grid layout
  - Click handlers for navigation
  - Visual feedback for selection

### 5. State Management
- Directory state is managed through:
  - `DirectoryScreenUIState` for UI representation
  - `DirectoryContents` for actual directory data
  - `Path` for current location tracking
- State updates flow through:
  - DirectoryNavigator's StateFlow
  - Combined flows in ViewModel
  - UI state updates in Screen

## State Transitions
```
IDLE (initial)
  → LOADING (during navigation)
  → SUCCESS (when directory loaded)
  → ERROR (if navigation fails)
  → IDLE (when error cleared)
```

## Error Handling
- Network errors during navigation
- Invalid directory paths
- Permission issues
- Connection timeouts
- Error states are displayed to user
- Navigation can be retried after errors

## Additional Features
- Directory sorting (name, date)
- Search filtering
- Path history tracking
- Breadcrumb navigation
- Home directory shortcut