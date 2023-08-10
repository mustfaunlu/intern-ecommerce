<h1 align="center">E-commerce App</h1></br>
<p align="center">  
A sample e-commerce app built to demonstrate the use of Modern Android development tools. E-commerce App is a modern Android application that uses the latest tools and technologies. Built with Kotlin and Android Architecture Components, it uses the latest cutting-edge technologies, components, and patterns.
</p></br>

<p align="center">
  <a href="https://android-arsenal.com/api?level=26"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/mustfaunlu"><img alt="Profile" src="https://img.shields.io/badge/github-mustfaunlu-blue"/></a> 
</p>


## Screenshots
<p align="center">
<img src="/previews/login-screen.png" width="20%"/>
<img src="/previews/login-screen-loading.png" width="20%"/>
<img src="/previews/product-list-screen.png" width="20%"/>
<img src="/previews/product-list-screen-1.png" width="20%"/>
<img src="/previews/category-screen.png" width="20%"/>
<img src="/previews/product-detail-screen.png" width="20%"/>
<img src="/previews/add-to-cart.png" width="20%"/>
<img src="/previews/shopping-list.png" width="20%"/>
<img src="/previews/shopping-list-1.png" width="20%"/>
</p>

## App Gif
<p align="center">
<img src="/previews/app.gif" width="30%"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 24
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) and [Flow](https://developer.android.com/kotlin/flow) & [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
    -  A single-activity architecture, using the [Navigation Component](https://developer.android.com/guide/navigation) to manage fragment navigation operations.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
    - [UseCases](https://developer.android.com/topic/architecture/domain-layer) - Located domain layer that sits between the UI layer and the data layer.
    - [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in data layer that contains application data and business logic.
- [Android Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency Injection Library
- [Retrofit](https://square.github.io/retrofit/) A type-safe HTTP client for Android and Java
- [OkHttp](https://square.github.io/okhttp/) An HTTP client that efficiently make network requests
- [Glide](https://github.com/bumptech/glide) An image loading and caching library for Android focused on smooth scrolling
- [ViewPager2](https://developer.android.com/jetpack/androidx/releases/viewpager2) ViewPager2 is the replacement of ViewPager, It is a widget that allows the user to swipe left or right to see an entirely new screen.
- [Moshi](https://github.com/square/moshi) Moshi is a modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes.
- [Room](https://developer.android.com/training/data-storage/room) The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
- [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) WorkManager is an API that makes it easy to schedule deferrable, asynchronous tasks that are expected to run even if the app exits or device restarts.
- [kJWT](https://github.com/nefilim/kjwt) Functional Kotlin & Arrow based library for generating and verifying JWTs and JWSs.
- [Firebase](https://firebase.google.com/) - Used for authentication, crashlytics, analytics, firestore and messaging.
    - [Firebase Authentication](https://firebase.google.com/docs/auth) Firebase Authentication provides backend services, easy-to-use SDKs, and ready-made UI libraries to authenticate users to your app.
    - [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics) Firebase Crashlytics is a lightweight, realtime crash reporter that helps you track, prioritize, and fix stability issues that erode your app quality.
    - [Firebase Analytics](https://firebase.google.com/docs/analytics) Firebase Analytics is a free app measurement solution that provides insight on app usage and user engagement.
    - [Firebase Firestore](https://firebase.google.com/docs/firestore) Cloud Firestore is a flexible, scalable database for mobile, web, and server development from Firebase and Google Cloud.
    - [Firebase Messaging(FCM)](https://firebase.google.com/docs/cloud-messaging) Firebase Cloud Messaging (FCM) is a cross-platform messaging solution that lets you reliably send messages at no cost.
    - [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences) Store private primitive data in key-value pairs.

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture

![](https://user-images.githubusercontent.com/21035435/69536839-9f4c8e80-0fa0-11ea-85ee-d7823e5a46b0.png)

## API
E-commerce App uses the [DummyJson](https://dummyjson.com/) for constructing RESTful API.<br>

