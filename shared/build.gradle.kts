import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.*
import com.codingfeline.buildkonfig.gradle.TargetConfigDsl.*

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("org.jetbrains.kotlin.native.cocoapods")
    id("com.codingfeline.buildkonfig")
//    id("com.chromaticnoise.multiplatform-swiftpackage") version "2.0.3"
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
                //useIR = true
            }
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "15.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Ktor.clientCore)
                implementation(Ktor.clientJson)
                implementation(Ktor.clientLogging)
                implementation(Ktor.clientSerialization)
                implementation(Ktor.clientContentNegotiation)
                implementation(Ktor.clientSerializationJson)

                // Serialize
                implementation(Serialization.core)

                // koin
                api(Koin.core)
                api(Koin.test)

                // kermit
                api(Deps.kermit)
//                implementation(Deps.datastore)
                implementation(Deps.coroutines)
                implementation("com.russhwolf:multiplatform-settings-coroutines:0.8.1")
                implementation("com.benasher44:uuid:0.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.clientAndroid)
                implementation(Deps.datastore)
                implementation("androidx.startup:startup-runtime:1.1.1")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
                implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")

            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Ktor.clientIos)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-apache:${Versions.ktor}")
            }
        }

    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 30
        targetSdk = 32
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi", "-Xjvm-default=all"
        )
    }
}

buildkonfig {
    packageName = "com.blackfox.myoutfitpicker"

    defaultConfigs {
        buildConfigField(
            type = STRING,
            name = "api_key",
            value = findProperty("rapidapikey") as? String ?: throw Exception("catsApiKey is not set")
        )
        buildConfigField(
            type = STRING,
            name = "weather_client_api_key",
            value = findProperty("weather_client_api_key") as? String ?: throw Exception("Need a local api key")
        )
        buildConfigField(
            type = STRING,
            name = "weather_client_host_name",
            value = findProperty("weather_client_host_name") as? String ?: throw Exception("Need a local host name")
        )
    }
}
