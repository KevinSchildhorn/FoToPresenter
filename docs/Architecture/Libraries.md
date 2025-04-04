# Libraries 
This document outlines the major libraries used in the shared module of FoToPresenter. These libraries are essential for the app's functionality across different platforms.

## UI and Compose

### Jetpack Compose
- **Version**: 1.7.3
- **Purpose**: Modern UI toolkit for building native Android UI
- **Components Used**:
  - `compose.runtime`
  - `compose.foundation`
  - `compose.material`
  - `compose.components.resources`

### Navigation Compose
- **Version**: 2.7.0-alpha07
- **Purpose**: Navigation component for Jetpack Compose
- **Usage**: Handles navigation between different screens in the app

### Eva Icons
- **Version**: 1.1.0
- **Purpose**: Icon library for Compose
- **Usage**: Provides a comprehensive set of icons for the UI

### Compose Shimmer
- **Version**: 1.3.1
- **Purpose**: Loading effect library for Compose
- **Usage**: Implements shimmer effects for loading states

## Image Loading and Processing

### Coil
- **Version**: 3.0.4
- **Purpose**: Image loading library
- **Usage**: Handles image loading and caching across platforms

### Kim
- **Version**: 0.8.3
- **Purpose**: Image metadata handling
- **Usage**: Processes and manages image metadata

## Data Management

### SQLDelight
- **Version**: 2.0.2
- **Purpose**: SQL database library
- **Usage**: Local database management for playlists and settings

### Kotlinx Serialization
- **Version**: 1.7.3
- **Purpose**: JSON serialization
- **Usage**: Data serialization and deserialization

### Multiplatform Settings
- **Version**: 1.2.0
- **Purpose**: Cross-platform settings management
- **Usage**: Handles app settings and preferences

## Networking

### SMBJ
- **Version**: 0.11.5
- **Purpose**: SMB protocol implementation
- **Usage**: Network file system access

## Caching

### Cache4k
- **Version**: 0.12.0
- **Purpose**: In-memory caching
- **Usage**: Fast data caching

### File Kache
- **Version**: 2.1.0
- **Purpose**: File-based caching
- **Usage**: Persistent data caching

## Dependency Injection

### Koin
- **Version**: 4.0.0
- **Purpose**: Dependency injection framework
- **Usage**: Manages app dependencies and services

## Logging

### Kermit
- **Version**: 2.0.5
- **Purpose**: Multiplatform logging
- **Usage**: Application logging across platforms

## Security

### Security Crypto
- **Version**: 1.1.0-alpha06
- **Purpose**: Cryptographic operations
- **Usage**: Secure data handling and encryption

## Date and Time

### Kotlinx DateTime
- **Version**: 0.6.1
- **Purpose**: Date and time handling
- **Usage**: Cross-platform date and time operations

## Testing

### Turbine
- **Version**: 1.2.0
- **Purpose**: Flow testing
- **Usage**: Testing Kotlin Flows

### Koin Test
- **Version**: 3.5.3
- **Purpose**: Koin testing utilities
- **Usage**: Testing dependency injection

## Development Tools

### KTLint
- **Version**: 0.0.26
- **Purpose**: Code style enforcement
- **Usage**: Maintains consistent code style

## Platform-Specific Libraries

### Android
- `androidx.activity:activity-compose` (1.10.0)
- `androidx.appcompat:appcompat` (1.7.0)
- `androidx.core:core-ktx` (1.15.0)

### Desktop
- Compose Desktop specific dependencies for desktop platform support 