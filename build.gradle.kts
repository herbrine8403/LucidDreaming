plugins {
    kotlin("plugin.serialization") version "1.9.20"
}

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
