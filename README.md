# Android MVP Architecture Sample (Kotlin + Retrofit) - Weather App

MVP Architecture is one of the most popular architecture to develop a maintanable and managable codebase. We are developing a sample `Weater Forecast` Android App with `MVP Architecture` using `Kotlin` language and `Retrofit` network calling library.

 For simplicity, in this project I don't use any dependency injection framework. With the same project concept there is [another repository](https://github.com/hasancse91/weather-app-android-mvp-dagger) where I implemented `Dagger 2 dependency injection library`. You can check that repository after this one.

**There is another repository for the same project in `MVVM Architecture.` Check MVVM repository [from here](https://github.com/hasancse91/weather-app-android-mvvm).**

<img src="https://raw.githubusercontent.com/hasancse91/weather-app-android-mvp-architecture/master/data/screenshot_1.png" width="250" height="444" />

### Project Description
We will develop a weather forecast Android Application with MVP architecture. The UI will be as like as above screenshot. There is a `Spinner` with some `City` name. After selection a city user need to hit the `View Weather` button. Then App will send request to Open Weather web API and show the weather information in the UI.

### Open Weather API
We will use [Open Weather Map API](https://openweathermap.org/api) for collecting weather information. To get the real weather information of a city, you need to sign up and get your own `APP ID`. Otherwise you can test the API with their sample `BASE URL` and sample `APP ID` without creating account.

### Project Setup
Clone the project and open it using Android Studio. Then open your `local.properties` file under `Gradle Scripts`. You need to specify the `base_url` and `app_id` in your `local.properties` file. Store your API secret in plain string file or Kotlin file is very risky. For security purpose it's better store in local.properties file than plain string/Kotlin file.

#### Use Sample API without creating account
Add below lines at the end of your `local.properties` file. Then run the project. You'll get dummy or static API response from Open Weather API.
```properties
#this is sample Base URL
base_url=https://samples.openweathermap.org/data/2.5/

#this is sample App ID of Open Weather API
app_id=b6907d289e10d714a6e88b30761fae22
```
#### Use Real APP ID after sign up and activation of your APP ID
After Sign up at the website collect your own `APP ID` from their [API Keys page](https://home.openweathermap.org/api_keys). Then add below lines with your APP ID at the end of `local.properties` file.
```properties
#this is real Base URL
base_url=http://api.openweathermap.org/data/2.5/

#this is real App ID of Open Weather API
app_id=YOUR_OWN_APP_ID
```
The BASE URL and APP ID will be fetched from `build.gradle` file and will be stored it in `BuildConfig`. And `Retrofit` API call will use the BASE URL and APP ID from `BuildConfig`.

**Note:** The free version of Open Weather API allows maximum 60 API calls per minute.
### Run the project
Sync the `Gradle` and run the project. Install APK on your emulator or real device. Turn on the internet of your testing device. For better understanding, please read the comments of every methods. Hope, these comments will help you to feel the `MVP Architecture`.
### Disclaimer
There are some other ways of implementation of `MVP`. There are some arguments about the responsibilities of `presenter` and `model`. After understanding the basic part of `MVP Architecture` you can test them one by one. For simplicity, I've ignored `dependency injection` in this project. I have created another repository of `MVP` with dependency injection `Dagger 2`. You can check `Android Weather App MVP Architecture with Dagger 2` repository [from here](https://github.com/hasancse91/weather-app-android-mvp-dagger).
