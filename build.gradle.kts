buildscript {
    val kotlinVersion: String by project
    println(kotlinVersion)

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.11.0")
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${kotlinVersion}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
        maven(url = "https://androidx.dev/snapshots/builds/7909927/artifacts/repository")
        maven(uri("https://plugins.gradle.org/m2/"))
    }
}

