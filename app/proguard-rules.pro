# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Mantener clases generadas por anotaciones de Room y sus métodos
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keep class **_Impl { *; }
-keep class **Dao { *; }
-dontwarn androidx.room.**

# Mantener clases de los modelos de datos
-keep class com.nicolascristaldo.foodrecipes.data.database.model.** { *; }

# Mantener clases de Jetpack Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Mantener clases de Kotlin Coroutines
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

# Mantener clases de ViewModel
-keep class * extends androidx.lifecycle.ViewModel { *; }
-dontwarn androidx.lifecycle.**

# Mantener clases de navegación
-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**

# Mantener clases de Retrofit
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*

# Mantener clases de Kotlinx Serialization
-keep class kotlinx.serialization.** { *; }
-dontwarn kotlinx.serialization.**
-keepclassmembers class ** {
    @kotlinx.serialization.Serializable *;
}

# Mantener clases de Dagger Hilt
-keep class dagger.** { *; }
-keep class **_HiltModules { *; }
-keep class **_HiltComponents { *; }
-dontwarn dagger.**
-keepattributes *Annotation*
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# Mantener clases de OkHttp
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**

# Mantener clases de recursos generados (R)
-keep class com.nicolascristaldo.foodrecipes.R { *; }
-keep class com.nicolascristaldo.foodrecipes.R$* { *; }

# Mantener clases de dominio y modelos de datos
-keep class com.nicolascristaldo.foodrecipes.domain.model.** { *; }
-keep class com.nicolascristaldo.foodrecipes.data.network.model.** { *; }

# Reglas generales para Kotlin
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class ** {
    @kotlin.Metadata *;
}

# Mantener métodos de reflexión usados por Kotlin
-keepclassmembers class ** {
    public static ** companion;
}

# Mantener clases de Flow de Kotlin
-keep class kotlinx.coroutines.flow.** { *; }

# Mantener clases de los providers
-keep class com.nicolascristaldo.foodrecipes.data.providers.** { *; }

# Evitar advertencias para clases de AndroidX
-dontwarn androidx.**

# Mantener atributos necesarios para la serialización y reflexión
-keepattributes SourceFile,LineNumberTable
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# Evitar optimizaciones agresivas que puedan romper la app
-dontoptimize
-dontshrink

# Mantener clases de tu aplicación que podrían ser accedidas por reflexión
-keep class com.nicolascristaldo.foodrecipes.** { *; }