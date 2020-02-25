object Dependencies {

    private const val kotlinVersions = "1.3.61"
    private const val buildGradleVersion = "3.5.0"

    object BuildGradle {
        const val buildGradle = "com.android.tools.build:gradle:$buildGradleVersion"
        const val buildGradleKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersions"
    }

    object Plugins {
        const val androidApplication = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val kotlinAndroid = "android"
        const val kotlinAndroidExtensions = "android.extensions"
        const val kotlinKapt = "kapt"
        const val navigation_safeargs = "androidx.navigation.safeargs"
        const val dataBinding = " com.android.databinding:compiler:$buildGradleVersion"
    }

    object UnitTest {
        private const val junitVersion = "4.12"
        private const val coreVersion = "1.1.0"
        private const val mockitoVersion = "1.10.19"
        private const val mockito2Version = "2.23.0"

        const val junit = "junit:junit:$junitVersion"
        const val testCore = "androidx.test:core:1.0.0"
        const val extJunit = "androidx.test.ext:junit:1.1.1"
        const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
        const val testRules = "androidx.test:rules:$coreVersion"
        const val testRunner = "androidx.test:runner:$coreVersion"
        const val mockitoCore = "org.mockito:mockito-core:$mockitoVersion"
        const val mockito2Core = "org.mockito:mockito-core:$mockito2Version"
    }

    object AndroidSupport {
        private const val constraintLayoutVersion = "1.1.3"
        private const val appCompatVersion = "1.1.0"
        private const val supportVersion = "1.0.0"

        const val support = "androidx.legacy:legacy-support-v4:$supportVersion"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val recyclerView = "androidx.recyclerview:recyclerview:$supportVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    }

    object Navigation {
        private const val nav_version = "2.1.0"

        const val dynamic_feature =
            "androidx.navigation:navigation-dynamic-features-fragment:2.3.0-alpha01"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
        const val ui = "androidx.navigation:navigation-ui-ktx:$nav_version"
    }

    object Camera {
        private const val camerax_version = "1.0.0-alpha09"
        private const val camerax_view_version = "1.0.0-alpha05"
        private const val camerax_ext_version = "1.0.0-alpha02"

        const val camerax_core = "androidx.camera:camera-core:$camerax_version"
        const val camera2 = "androidx.camera:camera-camera2:$camerax_version"
        const val camera_view = "androidx.camera:camera-view:$camerax_view_version"
        const val camera_extension = "androidx.camera:camera-extensions:$camerax_ext_version"
        const val camera_lifecycle = "androidx.camera:camera-lifecycle:1.0.0-alpha02"
    }

    object Kotlin {
        private const val ktxVersion = "1.1.0"

        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersions"
        const val ktxCore = "androidx.core:core-ktx:$ktxVersion"
    }

    object Dagger {
        private const val daggerVersion = "2.16"

        const val daggerProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
        const val daggerAndroid = "com.google.dagger:dagger-android:$daggerVersion"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
        const val dagger = "com.google.dagger:dagger:$daggerVersion"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.6.0"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:2.5.0"
        const val gson = "com.google.code.gson:gson:2.8.5"
    }

    object Room {

        private const val version = "2.2.0-rc01"

        const val roomRunTime = "androidx.room:room-runtime:$version"
        const val roomCompiler = "androidx.room:room-compiler:$version"
        const val roomKtx = "androidx.room:room-ktx:$version"
        const val roomTest = "androidx.room:room-testing:$version    "
    }

    object Rx {
        const val RxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
        const val RxJava = "io.reactivex.rxjava2:rxjava:2.2.14"
    }

    object Coroutines {
        private const val version = "1.3.2"

        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val coroutinesAdapter =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    }

    object Lifecycle {
        private const val version = "2.2.0"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val runTime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
    }

    object Glide {
        private const val version = "4.10.0"

        const val glide = "com.github.bumptech.glide:glide:$version"
        const val glide_compiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Other {
        const val zeloryCompressor = "id.zelory:compressor:2.1.0"
    }
}