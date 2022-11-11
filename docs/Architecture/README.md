# Architecture

FotoPresenter uses an **MVVM** Architecture(Model-View-ViewModel), which is mostly stored in the shared code.

In short, there's the UI Layer, which talks to the Domain Layer, which talks to the Data Layer(see below)
![architecture overview](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview.png)

This is the recommended architecture from [the android documentation](https://developer.android.com/topic/architecture), which can be used in common code as well. While there are many different architectures in iOS (MVC, VIPER, etc) a lot of developers are moving towards MVVM to work with SwiftUI.

## UI Layer
![](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-ui.png)

`me.kevinschildhorn.common.layers.ui`

Contains:
* **UI elements**, which are in the platform layers(Using Compose and SwiftUI)
* **ViewModels**, which are in the `commonMain` sourceset
* **UiState**, which are data classes in the `commonMain` sourceset that hold the state of the UI to be displayed (text, button states, etc)

## Domain Layer

`me.kevinschildhorn.common.layers.domain`

Contains **Use Cases** to reuse business logic. Note that it is generally recommended that you don't need `Use Cases` in a smaller application, but for clean architecture and wanting to work with `Uses Cases` I have added them to this project. These Use cases live in the `commonMain` sourceset.

## Data Layer
![](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-data.png)

`me.kevinschildhorn.common.layers.data`

Contains:
* **Data Sources**, which are in the `commonMain` sourceset
* **Repositories**, which live in the `commonMain` sourceset
* **Data** used in the sources and repositories, which are generally data classes

### Example of Data Flow
![AndroidArchitecture](photos/android_architecture.png)

The general hierarchy of the app is:
`UI -> ViewModel -> UseCase -> Repository -> DataSource`
