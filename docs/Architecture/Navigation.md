# Navigation

FoToPresenter uses Jetpack Compose Navigation (androidx.navigation.compose) for handling navigation between different screens in the application. The navigation system is implemented in a type-safe manner using an enum class to define the available screens.

## Available Screens

The application has four main screens defined in the `Screen` enum:

1. `LOGIN` - The initial screen where users enter their credentials
2. `DIRECTORY` - The main screen for browsing directories and images
3. `SLIDESHOW` - The screen for displaying image slideshows
4. `PLAYLIST` - The screen for managing playlists

## Navigation Implementation

The navigation is implemented in the `App` composable function, which sets up the `NavHost` with the following configuration:

- The navigation controller is created using `rememberNavController()`
- The start destination is set to `Screen.LOGIN`
- Each screen is registered as a composable route using the screen's name

### Navigation Flow

1. **Login Screen**
   - Initial screen when the app starts
   - After successful login, navigates to the Directory screen
   - Uses `LoginViewModel` for handling authentication

2. **Directory Screen**
   - Main screen for browsing files and folders
   - Contains a navigation rail for accessing playlists and logout
   - Can navigate to:
     - Slideshow screen (when starting a slideshow)
     - Playlist screen (via navigation rail)
     - Back to Login screen (when logging out)
   - Uses `DirectoryViewModelNew` for managing directory state

3. **Slideshow Screen**
   - Displays images in a slideshow format
   - Can navigate back to the Directory screen
   - Uses `SlideshowViewModel` for managing slideshow state

4. **Playlist Screen**
   - Manages playlists
   - Can navigate to the Slideshow screen when starting a playlist
   - Uses `PlaylistViewModel` for managing playlist state

## Navigation Components

### Navigation Rail

The app includes a navigation rail (`AppNavigationRail`) that provides quick access to:
- Playlists
- Logout functionality

### Directory Navigation

The `DirectoryNavigator` class handles navigation within the directory structure:
- Navigating up and down the directory tree
- Managing the current path
- Handling directory contents and sorting

## State Management

Each screen has its own ViewModel and state management:
- `LoginScreenState` - Manages login form state
- `DirectoryScreenUIState` - Manages directory view state
- `SlideshowScreenState` - Manages slideshow state
- `ImageScreenState` - Manages image selection state

## Navigation Dependencies

The navigation system relies on the following key dependencies:
- androidx.navigation.compose
- Jetpack Compose
- Koin for dependency injection
