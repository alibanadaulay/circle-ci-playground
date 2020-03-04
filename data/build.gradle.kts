plugins {
    id(Dependencies.Plugins.androidLibrary)
    kotlin(Dependencies.Plugins.kotlinAndroid)
    kotlin(Dependencies.Plugins.kotlinAndroidExtensions)
    kotlin(Dependencies.Plugins.kotlinKapt)
}

android {
    compileSdkVersion(Config.DefaultConfig.compileSdkVersion)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.Kotlin.kotlinStdlib)
    implementation(Dependencies.Kotlin.ktxCore)

    //Rx
    implementation(Dependencies.Rx.RxAndroid)
    implementation(Dependencies.Rx.RxJava)

    //Dagger
    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Dagger.daggerAndroid)
    implementation(Dependencies.Dagger.daggerAndroidSupport)
    kapt(Dependencies.Dagger.daggerCompiler)
    kapt(Dependencies.Dagger.daggerProcessor)
}