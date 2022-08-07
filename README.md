# *Foto* Presenter


Foto Presenter is an image browser and slideshow app that connects to a server via FTP. It uses Kotlin Multiplatform to share code between Android and iOS, and uses composable UIs


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
