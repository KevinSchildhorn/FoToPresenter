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
- `getDirectoryDetails(path: Path): NetworkDirectoryDetails`: Retrieves details about a specific directory (i.e. Name and Path)
- `getDirectoryContents(path: Path): List<NetworkDirectoryDetails>`: Lists contents of a directory
- `openDirectory(path: Path): Path?`: Opens a directory for access
- `folderExists(path: Path): Boolean?`: Checks if a directory exists

### File Operations
- `getSharedImage(path: Path): SharedImage?`: Gets a Shared Image, containing a ByteArray, from the path
- `savePlaylist(playlistName: String, json: String)`: Saves playlist data to a json file
- `getPlaylists()`: Retrieves all playlists
- `deletePlaylist(playlistName: String)`: Removes a playlist

## SMBJHandler Implementation

The `SMBJHandler` is the concrete implementation of `NetworkHandler` using the SMBJ library for SMB/CIFS protocol support. It provides:

### Connection Management
- Uses `SMBClient` for establishing connections
- Maintains connection state through `Connection`, `Session`, and `DiskShare` objects (`com.hierynomus.smbj`)
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

## Error Handling

The networking system uses a custom exception class `NetworkHandlerException` with specific error types:

- `NOT_CONNECTED`: When operations are attempted without an active connection
- `DIRECTORY_NOT_FOUND`: When attempting to access a non-existent directory
- `FILE_NOT_FOUND`: When attempting to access a non-existent file

## Dependencies

- SMBJ Library: For SMB/CIFS protocol support
- Kotlin Coroutines: For asynchronous operations
- Kermit: For logging
