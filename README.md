# Google SignIn with Jetpack compose!
## Adding Firebase Authentication and google-services.json file to a Jetpack Compose project

To add Firebase Authentication and `google-services.json` file to a Jetpack Compose project, you will need to follow these steps:

1. Create a project in the [Firebase Console](https://console.firebase.google.com/), if you don't already have one.
2. In the "Add Firebase to your app" section, select the Android platform and enter your package name and app name.
3. Download the `google-services.json` file provided by the Firebase Console and place it in the `app` folder of your Jetpack Compose project.
4. Add the Firebase Authentication dependency to your app module's `build.gradle` file:

```gradle
implementation 'com.google.firebase:firebase-auth-ktx:21.0.1'

```
<br/>

## Implementation

<div style="background-color: #f2f2f2; width: 500px; height: 500px; padding: 20px;">

    // Navigation Compose
    implementation "androidx.navigation:navigation-compose:2.5.3"

    // Dagger - Hilt
    implementation "com.google.dagger:hilt-android:2.44.2"
    kapt "com.google.dagger:hilt-android-compiler:2.44.2"
    kapt 'androidx.hilt:hilt-compiler:1.0.0'
    implementation 'androidx.hilt:hilt-navigation-compose:1.0.0'

    //Google auth dependency
    implementation 'com.google.android.gms:play-services-auth:20.4.1'

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.2")
</div>

## Dagger-Hilt

[Dagger-Hilt](https://dagger.dev/hilt/) is an Android dependency injection library that helps simplify the setup of dependency injection components and modules in your project. With Jetpack Compose, you can use Dagger-Hilt to inject dependencies into your composition functions, such as ViewModel and other classes required for your UI to function.

## Coil

[Coil](https://coil-kt.github.io/coil/) is an Android image loading library that is compatible with Jetpack Compose. With Coil, you can load images from URLs and other resources into composed visual elements, such as `Image`. Coil offers various configuration options, such as maximum cache size, connection timeout, and other parameters that help improve application performance.

## MVVM

The [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) (Model-View-ViewModel) pattern is an architecture pattern that helps separate the business logic from the UI in an Android application. With Jetpack Compose, you can use the MVVM pattern to manage the UI state and control the business logic in a clear and organized manner.

## Navigation

[Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) is an Android navigation library that helps manage the navigation flow between screens of your application. With Jetpack Compose, Navigation is used to manage navigation on composed screens, allowing users to navigate to other screens and go back to the previous screen, while maintaining the integrity of the application's navigation information.

----------------------------------------------------------------------------
----------------------------------------------------------------------------
----------------------------------------------------------------------------

# Thank you!

Thank you for using this repository and I hope it has helped you in your project! If you have any further questions or feedback, feel free to reach out.

Enjoy the resources and keep creating amazing projects!






