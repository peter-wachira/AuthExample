![Build Application](https://github.com/ProtoEnergy/RetailDriver/workflows/Build%20Application/badge.svg)


# AuthExample
This is reusable authentication code, the app uses the MVVM
design pattern for getting data from the network.

## Prerequisite

minSdkVersion -> 16

Gradle build system


## TOC

- [Architecture](#architecture)
- [Flow](#flow)
- [Libraries](#libraries)
- [Extras](#extras)

## Architecture

The App is not organized into multiple modules but follows the same principles of
the Presentation, Domain, and Data Layers.
The presentation layer handles the UI work with the logic contained in the **ViewModel**.
The UI uses a **LiveData** object from the ViewModel and observes it using the **Observer Pattern**.
**retrofit** and **coroutines** to handle background work asynchronously. Additionally, note that
the ViewModel uses the **viewModelScope** to launch the coroutines while Fragments use the **viewLifeCycleOwner**
to observe data.


## Libraries

This app takes use of the following libraries:

- [Jetpack](https://developer.android.com/jetpack)🚀
  - [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI data to survive configuration changes and is lifecycle-aware
  - [Navigation](https://developer.android.com/guide/navigation/) - Handle everything needed for in-app navigation
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Notify views when underlying database changes
- [Retrofit](https://square.github.io/retrofit/) - type safe http client with coroutines support
- [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - logging HTTP request related data.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines



### To come (if needed)

  - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Declaratively bind observable data to UI elements
  - [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - Manage your Android background jobs
  - [Room DB](https://developer.android.com/topic/libraries/architecture/room) - Fluent SQLite database acces
  - [Dagger Hilt](https://dagger.dev/hilt/) - A fast dependency injector for Android and Java.
  - [Paging](https://developer.android.com/topic/libraries/architecture/paging) - Load and display small chunks of data at a time
  - [Material Design](https://material.io/develop/android/docs/getting-started/) - build awesome beautiful UIs.🔥🔥

