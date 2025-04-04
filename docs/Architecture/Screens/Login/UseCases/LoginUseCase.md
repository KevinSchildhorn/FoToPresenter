# Login Use Case

## Overview
The login use case handles the authentication process for connecting to a server. It involves multiple components working together to provide a secure and user-friendly login experience.

![Login Flow](../../../../UML/LoginFlow.drawio.png)

## Code Flow

### 1. User Interaction (LoginScreen)
- User enters credentials in the form fields:
  - `LoginTextField` for hostname
  - `LoginTextField` for username
  - `LoginPasswordTextField` for password (with show/hide functionality)
  - `LoginTextField` for shared folder
  - `LoginCheckbox` for auto-connect option
- Each field update triggers a corresponding function in the ViewModel:
  - `updateHost(hostname: String)`
  - `updateUsername(username: String)`
  - `updatePassword(password: String)`
  - `updateSharedFolder(sharedFolder: String)`
  - `updateShouldAutoConnect(shouldAutoConnect: Boolean)`
- The login button state is controlled by `LoginScreenState.isLoginButtonEnabled`
- When the user clicks the login button, it calls `LoginViewModel.login()`

### 2. ViewModel Processing (LoginViewModel)
- When `login()` is called:
  - Sets UI state to `LOADING`
  - Launches a coroutine on `Dispatchers.Default`
  - Calls `networkHandler.connect()` with the current credentials
  - Handles the connection result:
    - On success: 
      - Updates UI state to `SUCCESS`
      - Calls `credentialsRepository.saveCredentials()` with the current values
    - On failure:
      - Updates UI state to `ERROR`
      - Logs the error

### 3. Data Management (LoginCredentials)
- The `LoginCredentials` data class holds all login information
- `isComplete` property validates that all required fields are filled
- Used to convert UI state to network-ready format via `asLoginCredentials`

### 4. Persistence (CredentialsRepository)
- When login succeeds, `saveCredentials()` is called with:
  - hostname
  - username
  - password
  - sharedFolder
  - shouldAutoConnect
- The repository updates the `CredentialsDataSource` with the new values
- On app startup, `fetchCredentials()` retrieves saved credentials

### 5. Auto-Connect Flow
- On app initialization:
  - `LoginViewModel` checks for saved credentials
  - If credentials exist and `shouldAutoConnect` is true:
    - Calls `attemptAutoLogin()`
    - Uses `UseCaseFactory.autoConnectUseCase` to attempt connection
    - Updates UI state based on connection result

### 6. Navigation
- When login succeeds (UI state becomes `SUCCESS`):
  - `LoginScreen` detects the success state
  - Calls the `onLoginSuccess` callback
  - User is navigated to the Directory screen

## State Transitions
```
IDLE (initial) 
  → LOADING (during connection attempt)
  → SUCCESS (on successful login) or ERROR (on failed login)
  → IDLE (if user retries after error)
```

## Error Handling
- Network errors are caught in the ViewModel's login coroutine
- Error state includes a generic error message
- UI shows an `ErrorView` component when in ERROR state
- User can retry login after correcting credentials
