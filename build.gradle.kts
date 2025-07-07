buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.11.1")
        classpath("com.google.gms:google-services:4.4.1")
        classpath("io.realm:realm-gradle-plugin:10.18.0")
    }
}

/*buildscript {
    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.11.1")
    }
}*/

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.11.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}