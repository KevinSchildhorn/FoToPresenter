# Networking Documentation

This document describes the networking implementation in the FoToPresenter application, which handles server connections and file operations.

## Overview

The networking system consists of two main components:

1. **NetworkHandler**: An interface that defines the contract for network operations
2. **SMBJHandler**: The concrete implementation using the SMBJ library for SMB/CIFS protocol

## NetworkHandler Interface

The `NetworkHandler` interface provides a standardized way to interact with network storage systems. It defines the following key operations:

### Connection Management
- `isConnected`: Boolean property indicating connection status
- `connect(credentials: LoginCredentials)`: Establishes connection to the server
- `disconnect()`: Closes the connection and cleans up resources

### Directory Operations
- `getDirectoryDetails(path: Path)`: Retrieves details about a specific directory
- `getDirectoryContents(path: Path)`: Lists contents of a directory
- `openDirectory(path: Path)`: Opens a directory for access
- `folderExists(path: Path)`: Checks if a directory exists

### File Operations
- `openImage(path: Path)`: Opens and reads an image file
- `savePlaylist(playlistName: String, json: String)`: Saves playlist data
- `getPlaylists()`: Retrieves all playlists
- `deletePlaylist(playlistName: String)`: Removes a playlist

### Metadata Operations
- `setMetadata(json: String)`: Saves metadata information
- `getMetadata()`: Retrieves stored metadata

## SMBJHandler Implementation

The `SMBJHandler` is the concrete implementation of `NetworkHandler` using the SMBJ library for SMB/CIFS protocol support. It provides:

### Connection Management
- Uses `SMBClient` for establishing connections
- Maintains connection state through `Connection`, `Session`, and `DiskShare` objects
- Handles authentication using provided credentials

### Security Features
- Secure credential handling
- Proper resource cleanup on disconnect
- Error handling for connection failures

### File Access Configuration
- Configures file access masks for read operations
- Sets appropriate file attributes and share access permissions
- Handles file creation and opening dispositions

### Error Handling
The implementation includes robust error handling for:
- Connection failures
- Authentication errors
- File access issues
- Directory navigation problems

## Usage Example

```kotlin
// Initialize the handler
val networkHandler: NetworkHandler = SMBJHandler

// Connect to server
val credentials = LoginCredentials(
    hostname = "server.example.com",
    username = "user",
    password = "pass",
    sharedFolder = "Photos",
    shouldAutoConnect = false
)
val connected = networkHandler.connect(credentials)

// Access directory contents
if (connected) {
    val contents = networkHandler.getDirectoryContents(Path("Photos"))
    // Process contents...
}

// Cleanup
networkHandler.disconnect()
```

## Error Handling

The networking system uses a custom exception class `NetworkHandlerException` with specific error types:

- `NOT_CONNECTED`: When operations are attempted without an active connection
- `DIRECTORY_NOT_FOUND`: When attempting to access a non-existent directory
- `FILE_NOT_FOUND`: When attempting to access a non-existent file

## Best Practices

1. Always check `isConnected` before performing operations
2. Use try-catch blocks to handle `NetworkHandlerException`
3. Properly disconnect when done to free resources
4. Handle authentication failures gracefully
5. Implement proper error messaging for users

## Dependencies

- SMBJ Library: For SMB/CIFS protocol support
- Kotlin Coroutines: For asynchronous operations
- Kermit: For logging
