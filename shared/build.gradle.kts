plugins {
    kotlin("multiplatform") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.20"
    kotlin("native.cocoapods") version "1.9.20"
    id("com.android.library") version "8.2.0"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                
                // Serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
                
                // Ktor Client
                implementation("io.ktor:ktor-client-core:2.3.7")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
                implementation("io.ktor:ktor-client-logging:2.3.7")
            }
        }
        androidMain {
            dependencies {
                implementation("androidx.core:core-ktx:1.12.0")
                implementation("androidx.datastore:datastore-preferences:1.0.0")
                implementation("io.ktor:ktor-client-android:2.3.7")
            }
        }
        iosMain {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.7")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                api("io.ktor:ktor-client-core:2.3.7")
            }
        }
    }
}

android {
    namespace = "com.luciddreaming.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

cocoapods {
    version = "1.0.0"
    summary = "LucidDreaming shared module"
    homepage = "https://github.com/herbrine8403/LucidDreaming"
    
    ios.deploymentTarget = "15.0"
    
    framework {
        baseName = "Shared"
        
        // Required by Kotlin/Native to work with Coroutines
        export("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        
        // Required by Ktor
        export("io.ktor:ktor-client-core:2.3.7")
        export("io.ktor:ktor-client-content-negotiation:2.3.7")
    }
    
    podfile = project.file("../iosApp/Podfile")
}
