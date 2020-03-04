plugins {
    id(Dependencies.Plugins.androidApplication)
    kotlin(Dependencies.Plugins.kotlinAndroid)
    kotlin(Dependencies.Plugins.kotlinAndroidExtensions)
    kotlin(Dependencies.Plugins.kotlinKapt)
}

android {
    dataBinding.isEnabled = true
    compileSdkVersion(Config.DefaultConfig.compileSdkVersion)
    defaultConfig {
        applicationId = "com.ali.circle_ci_playground"
        minSdkVersion(Config.DefaultConfig.androidMinSdk)
        targetSdkVersion(Config.DefaultConfig.androidSdkTarget)
        versionCode = Config.DefaultConfig.versionCode
        versionName = Config.DefaultConfig.versionName
        testInstrumentationRunner = Config.testInstrumentationRunner
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kapt {
        generateStubs = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //support
    implementation(Dependencies.AndroidSupport.constraintLayout)
    implementation(Dependencies.AndroidSupport.recyclerView)
    implementation(Dependencies.AndroidSupport.support)

    implementation(Dependencies.Kotlin.kotlinStdlib)
    implementation(Dependencies.Kotlin.ktxCore)

    //Coroutines (Background Handler)
    implementation(Dependencies.Coroutines.coroutinesAndroid)
    implementation(Dependencies.Coroutines.coroutinesCore)
    implementation(Dependencies.Coroutines.coroutinesAdapter)

    //Rx
    implementation(Dependencies.Rx.RxAndroid)
    implementation(Dependencies.Rx.RxJava)

    //Dagger
    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Dagger.daggerAndroid)
    implementation(Dependencies.Dagger.daggerAndroidSupport)
    kapt(Dependencies.Dagger.daggerCompiler)
    kapt(Dependencies.Dagger.daggerProcessor)

    //Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofitGson)
    implementation(Dependencies.Retrofit.gson)
    implementation(Dependencies.Retrofit.rxJavaConverter)

    //Room
    implementation(Dependencies.Room.roomRunTime)
    implementation(Dependencies.Room.roomKtx)
    kapt(Dependencies.Room.roomCompiler)

    //Test
    testImplementation(Dependencies.UnitTest.junit)
    testImplementation(Dependencies.UnitTest.testCore)
    testImplementation(Dependencies.UnitTest.testRunner)
    testImplementation(Dependencies.UnitTest.testRules)
    testImplementation(Dependencies.UnitTest.extJunit)
    testImplementation(Dependencies.UnitTest.mockito2Core)
    testImplementation(Dependencies.UnitTest.mockWebServer)
    testImplementation(Dependencies.UnitTest.mockkAndroid)
    testImplementation(Dependencies.UnitTest.mockk)
    androidTestImplementation(Dependencies.UnitTest.mockkAndroid)

    //LiveData
    implementation(Dependencies.Lifecycle.viewModel)
    implementation(Dependencies.Lifecycle.runTime)
}
