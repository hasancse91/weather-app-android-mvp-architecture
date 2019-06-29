# Weather Forecast Android App with MVP Architecture - (Kotlin + Retrofit)

MVP Architecture is one of the most popular architecture to develop a maintanable and managable codebase. We are developing a sample `Weater Forecast` Android App with `MVP Architecture` using `Kotlin` language and `Retrofit` network calling library.

<img src="https://raw.githubusercontent.com/hasancse91/weather-app-android-mvp-architecture/master/data/screenshot_1.png" width="250" height="444" />

### Use your own App ID for real weather forecast
We will use [Open Weather Map API](https://openweathermap.org/api) for collecting weather information. Before start, you need to sign up and get your own `APP ID` to use there weather API. After activation of your API key you can use it to run this project.

Clone the project and open `Config.kt` file (app/src/main/java/com/hellohasan/weatherforecast/Config.kt). Add your `App ID` as a value of `APP_ID` variable. Then run the project!

### Use sample App ID and sample API BASE URL if you don't want to sign up or your App ID is not yet activated

If you don't want to sign up to this site and no need real weather information you can use sample Base URL and sample App ID to observe the API call with Retrofit with MVP.

Clone this project and open `Config.kt` file (app/src/main/java/com/hellohasan/weatherforecast/Config.kt). Use the sample Base URL and sample App ID as below:

```kotlin
class Config {
    companion object {
        const val BASE_URL = "https://samples.openweathermap.org/data/2.5/" //sample base url
        const val APP_ID = "b6907d289e10d714a6e88b30761fae22" //sample App ID
    }
}
```
Now you can run the project and will receive data from API. But the data will be same for every API request. And every time you'll get the weather data of the same city. For real data of all city, you need to use your own App ID.
