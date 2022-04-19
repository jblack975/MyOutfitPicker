A Kotlin Multiplatform project to help people figure out what clothing to wear, based on their 
calendar events, the expected weather for where they are and where they will be and personal
preferences. It will later use machine learning to help, but initially it will allow them to 
anonymously give data to help train the model.

This is also an example of how to use some of the modern libraries in a KMP application and you can see the video at 

I have only started the server subproject within Android Studio and tested it with curl.

For the Android project to call the server subproject change in gradle.properties this line:
weather_client_host_name=http://10.0.2.2:8080

There is an issue at times when building where it may complain that it can't find some library but if you do a
Build -> Rebuild Project
It seems to go away.

Minimal **Kotlin Multiplatform** project with SwiftUI, Jetpack Compose, along with Ktor backend. Currently running on
* Android (Jetpack Compose)
* iOS (SwiftUI)
* JVM (small Ktor back end service + `Main.kt` in `shared` module)

These are in the code but not actually being used yet.
* Android App Widget (Compose based Glance API - contributed by https://github.com/yschimke)
* iOS App Widget (SwiftUI)
