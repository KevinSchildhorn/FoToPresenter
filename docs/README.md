# FoToPresenter Documentation

This directory contains comprehensive documentation for the FoToPresenter application. 
Below is a guide to the available documentation files and their purposes.

## Documentation Structure

### Planning

These documents go over how I planned out this application from the ground up, from research into UX.

- [UX](UX) - Goes over the UX Research that helped define the project. Contains User Stories and Use Cases.
- [Testing](Testing.md) - How testing is handled to cover all use cases and as much code coverage as possible.

### Overview Documentation
- [Architecture](Architecture/Architecture.md) - Explains the overall architecture of the application, including the MVVM pattern, data flow, and component relationships.
- [Libraries](Architecture/Libraries.md) - Lists and describes all major libraries used in the shared module, their versions, and purposes.
- [Navigation](Architecture/Navigation.md) - Details the navigation system using Jetpack Compose Navigation, including available screens and navigation flows.
- [Networking](Architecture/Networking.md) - Explains the networking implementation in the FoToPresenter application, including the NetworkHandler interface.'

### Screen Documentation
- [Login Screen](Architecture/Screens/Login) - Documents The Login Screen
- [Directory Screen](Architecture/Screens/Directory) - Documents The Login Screen
 
### Feature Documentation
- [ImageLoading](Architecture/ImageLoading.md) - Documents the image loading system, including caching strategies and image processing.

## How to Use This Documentation

1. Start with [Architecture.md](Architecture/Architecture.md) to understand the overall structure of the application.
2. Review [Libraries.md](Architecture/Libraries.md) to familiarize yourself with the dependencies.
3. Explore feature-specific documentation based on what you're working on.
4. Refer to technical documentation for implementation details.
5. Follow development guides when contributing to the project.

## Documentation Updates

When adding new features or making significant changes to the application:
1. Update the relevant documentation files
2. Add new documentation files if needed
3. Update this README.md if the documentation structure changes
4. Ensure all documentation is clear, concise, and up-to-date
