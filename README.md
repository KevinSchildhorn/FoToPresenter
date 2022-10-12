# *Foto* Presenter


Foto Presenter is an image browser and slideshow app that connects to a server via FTP. It uses Kotlin Multiplatform to share code between Android and iOS, and uses composable UIs

## Overview


Foto Presenter is an image browser and slideshow app that connects to a server via FTP. It should be made up of three main components:

1. Signing into an FTP Server
2. Browsing and navigating a directory of subdirectories and files
3. Starting a slideshow of photos

## Goals

In addition to being a full fledged app, this Repo is meant as an example of the full process of making an app. This goes from concepts and user stories to designing UI to the final application.

In addition to all of that, this will be a testing ground for sharing UI code between iOS and Android via KMP. Obviously you can't mix Jetpack Compose and SwiftUI per say, however things like Color, font, spacing, sizing, etc will be tested.

### Libraries

Foto will be designed to use the following Libraries and SDKs:

#### Android
* `Jetpack Compose`
* [FTPClient](https://commons.apache.org/proper/commons-net/apidocs/org/apache/commons/net/ftp/FTPClient.html)

#### iOS
* `SwiftUI`
* Potentially `NSURLSession`, or `CFFTPStream` (sample [here](https://developer.apple.com/library/archive/samplecode/SimpleFTPSample/Introduction/Intro.html))

## Conceptual Goals

While working on this project, there are some key things to make sure:

* The app should keep in the best practices for both iOS and Android, *equally*. This means that The android side and the iOS side should be written in the expected, cleanest approach as possible. Then the shared library should facilitate that style
* Share as much code as possible. This may sound like it's contradicting the first point, but there should be a sweet spot. Things like network calls and conceptual points should be shared, while UI work and general naming conventions and style guidelines should match
