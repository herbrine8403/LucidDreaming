# Keep SLF4J logging classes
-keep class org.slf4j.** { *; }
-keep interface org.slf4j.** { *; }
-dontwarn org.slf4j.**

# Keep Kotlin coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Keep Kotlinx serialization
-keepattributes *Annotation*
-keep @kotlinx.serialization.Serializable class * {*;}
-keepclassmembers class * {
    @kotlinx.serialization.SerialName <fields>;
}

# Keep Ktor client
-keep class io.ktor.** { *; }
-keep interface io.ktor.** { *; }
-dontwarn io.ktor.**

# Keep Compose
-keep class androidx.compose.** { *; }
-keep interface androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Keep ViewModel
-keep class * extends androidx.lifecycle.ViewModel {
    <init>();
}
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>();
}

# Keep DataStore
-keep class androidx.datastore.** { *; }
-keep interface androidx.datastore.** { *; }
-dontwarn androidx.datastore.**