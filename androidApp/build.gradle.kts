plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.blackfox.myoutfitpicker.android"
        minSdk = 30
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    testOptions {
        animationsDisabled = true
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.blackfox.myoutfitpicker.android"
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(Compose.runtime)

    implementation(Compose.ui)
    implementation(Compose.uiGraphics)
    implementation(Compose.uiTooling)
    implementation(Compose.foundationLayout)
    implementation(Compose.material)
    implementation(Compose.navigation)

    implementation("androidx.glance:glance-appwidget:1.0.0-alpha04")

    implementation("androidx.biometric:biometric:1.2.0-alpha04")
    // kermit
    implementation(Deps.kermit)

    implementation(Koin.core)
    implementation(Koin.android)
    implementation(Koin.compose)

    implementation(Ktor.clientAndroid)
    implementation(Ktor.clientCore)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.4.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
}
