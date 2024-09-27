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

# Keep Retrofit classes and methods
-keep class retrofit2.** { *; }
-keepclassmembers class retrofit2.** { *; }
-keepattributes Signature

# Keep OkHttp classes and methods
-keep class okhttp3.** { *; }
-keep class okio.** { *; }

# If you're using Gson for serialization, keep Gson classes
-keep class com.google.gson.** { *; }

# Keep kotlinx.coroutines.* classes
-dontwarn kotlinx.coroutines.**
-keep class kotlin.coroutines.** { *; }
-keep class kotlinx.coroutines.** { *; }

# Keep the @Keep annotations
-keepattributes *Annotation*
-keepclasseswithmembers class * {
    @kotlin.Metadata <methods>;
}

# Keep Parcelize classed
-keep @kotlinx.android.parcel.Parcelize public class *

# Keep app packages and files
-keep class com.flexath.currencyapp.data.** { *; }
-keep class com.flexath.currencyapp.domain.model.** { *; }
-keep class com.flexath.currencyapp.domain.repository.** { *; }

-keep class com.flexath.currencyapp.presentation.constants.** { *; }