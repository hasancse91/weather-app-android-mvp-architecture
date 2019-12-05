# Android MVP Architecture Sample Weather App - (Kotlin + Retrofit + MVP)

MVP Architecture is one of the most popular architecture to develop a maintanable and managable codebase. We are developing a sample `Weater Forecast` Android App with `MVP Architecture` using `Kotlin` language and `Retrofit` network calling library.

<img src="https://raw.githubusercontent.com/hasancse91/weather-app-android-mvp-architecture/master/data/screenshot_1.png" width="250" height="444" />

### Use your own App ID for real weather forecast
We will use [Open Weather Map API](https://openweathermap.org/api) for collecting weather information. Before start, you need to sign up and get your own `APP ID` to use there weather API. After activation of your API key you can use it to run this project.

Clone the project and open it using Android Studio. Then open your `local.properties` file under `Gradle Scripts`. You need to specify the BASE URL and APP ID in your `local.properties` file.
Without creating your own API key of Open Weather you can just test the API with dummy or static response. To do so add below lines at the end of your `local.properties` file.
```
#this is sample Base URL
base_url=https://samples.openweathermap.org/data/2.5/

#this is sample App ID of Open Weather API
app_id=b6907d289e10d714a6e88b30761fae22
```
If you have already your own APP ID then you can use below lines at the end of the `local.properties` file.
```
#this is real Base URL
base_url=http://api.openweathermap.org/data/2.5/

#this is real App ID of Open Weather API
app_id=YOUR_OWN_APP_ID
```
The BASE URL and APP ID will be fetched from `build.gradle` file and will be stored it in `BuildConfig`. And `Retrofit` API call will use the BASE URL and APP ID from `BuildConfig`.
