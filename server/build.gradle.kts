import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
    kotlin("jvm")
    id("kotlin-platform-jvm")
    id("war")
    id("application")
    kotlin("plugin.serialization")
    id("kotlinx-serialization")
}

group = "com.blackfox.myoutfitpicker"
application {
    mainClass.set("com.blackfox.myoutfitpicker.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(project(":shared"))
    implementation("io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}")
    implementation("io.ktor:ktor-server-core:${Versions.ktor}")
    implementation("io.ktor:ktor-server-netty:${Versions.ktor}")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("io.ktor:ktor-serialization:${Versions.ktor}")
    implementation(Ktor.clientJson)
    implementation("io.ktor:ktor-server-call-logging:${Versions.ktor}")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:${Versions.ktor}")
    implementation(Ktor.clientSerializationJson)

    testImplementation("io.ktor:ktor-server-test-host:${Versions.ktor}")
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    implementation(Ktor.clientCore)
    implementation(Ktor.clientJson)
    implementation(Ktor.clientLogging)
    implementation(Ktor.clientSerialization)
    implementation(Ktor.clientContentNegotiation)
    implementation(Ktor.clientSerializationJson)
    implementation(Serialization.core)

    implementation("io.ktor:ktor-server-status-pages:${Versions.ktor}")
    implementation("io.ktor:ktor-server-call-logging:${Versions.ktor}")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.10")
}

kotlin {
    sourceSets.all {
        languageSettings {
            optIn("kotlin.RequiresOptIn")
        }
    }
}


/*
appengine {
    stage {
        setArtifact(tasks.named("bootJar").flatMap { (it as Jar).archiveFile })
    }
    deploy {
        projectId = "outfit-picker"
        version = "GCLOUD_CONFIG"
    }
}
 */
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

