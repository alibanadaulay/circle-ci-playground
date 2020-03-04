buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(Dependencies.BuildGradle.buildGradle)
        classpath(Dependencies.BuildGradle.buildGradleKotlin)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}