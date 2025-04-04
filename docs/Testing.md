# Testing

In order to fully test the application and all of its logic, we will test every section of code possible. This means:

## UI

Generally with the UI/Application Layer there are two types of elements: `Composable` Screens and `ViewModels`.

### Screens

For Testing Screens we will use UI testing utilizing [Compose UI Testing](https://developer.android.com/develop/ui/compose/testing). This is not screenshot testing, but rather checking nodes to see if the UI is correct.

With the nature of a gallery app previewing network images, testing may be difficult. This is also why screenshot testing isn't being added currently.

#### How are tests defined

The testing will be based on the [UX](../docs/UX/README.md) Use cases. Tests should cover each use case. 

### ViewModel

ViewModels will be tested using Unit tests. The UiState will be tested using [Turbine](https://github.com/cashapp/turbine).

#### How are tests defined

The tests will be based on functions of the ViewModel. For every public function there should be a test.

## Data

The data layer is mostly comprised of `Repositories` and `DataSources`. These will both be tested using Unit Tests.

#### How are tests defined

The tests will be based on functions of the classes. For every public function there should be a test.