# FoToPresenter

FoToPresenter is a Kotlin Multiplatform application that allows users to present and manage images across multiple platforms (Android, Desktop, and iOS). The application provides a modern, responsive interface for image presentation and management.

## Features

- Cross-platform support (Android, Desktop, iOS)
- Modern UI built with Jetpack Compose
- Efficient image loading and caching
- Secure file handling
- SMB (Windows File Sharing) support
- SQLite database for local storage

## Technology Stack

| For more in depth technical docs, check the [docs](docs/) directory.

### Core Libraries
- **Kotlin Multiplatform**: For cross-platform development
- **Jetpack Compose**: Modern UI toolkit for building native interfaces
- **Coil**: Image loading and caching
- **SQLDelight**: Type-safe SQL API
- **Koin**: Dependency injection
- **Kotlinx Serialization**: JSON serialization
- **Cache4k**: In-memory caching
- **Kermit**: Logging
- **Multiplatform Settings**: Cross-platform settings management
- **Kim**: File management
- **File Kache**: File caching

### Platform-Specific Features
- **Android**: Native Android integration with Activity Compose
- **Desktop**: JVM-based desktop application
- **iOS**: Native iOS framework (currently disabled)

## Image Handling

The application uses Coil for efficient image loading and caching across platforms. Images are:
- Loaded asynchronously to prevent UI blocking
- Cached in memory and on disk for better performance
- Displayed using Compose's Image components
- Supported across all platforms with platform-specific optimizations

## Project Structure

- `shared/`: Core business logic and UI components
- `androidApp/`: Android-specific implementation
- `desktopApp/`: Desktop-specific implementation
- `iosApp/`: iOS-specific implementation
- `docs/`: Project documentation

## Building the Project

The project uses Gradle with Kotlin DSL for build management. To build the project:

1. Ensure you have the latest JDK installed
2. Run `./gradlew build` for all platforms
3. For specific platforms:
   - Android: `./gradlew :androidApp:assembleDebug`
   - Desktop: `./gradlew :desktopApp:run`
   - iOS: (Currently disabled)

## Documentation

For more detailed information about the project, check the [docs](docs/) directory.