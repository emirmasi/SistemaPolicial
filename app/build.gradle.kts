
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.services)
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.practica.policeubgapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.practica.policeubgapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.6.10"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.foundation.layout)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.firebase.appcheck.debug)
    debugImplementation(libs.compose.ui.tooling)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    //appCheck
    implementation(libs.firebase.appcheck.playintegrity)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.firestore)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)

    // Maps SDK for Android
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation(libs.google.maps)
    implementation(libs.google.play.services.location)
    implementation(libs.google.play.services.maps)
    implementation("com.google.maps.android:android-maps-utils:3.8.2")

    implementation("com.google.accompanist:accompanist-permissions:0.34.0")


    //coil
    implementation(libs.coil)

    //test
    testImplementation("org.mockito:mockito-core:5.11.0")
    androidTestImplementation("androidx.test:rules:1.5.0")
    // Para mockear funciones finales y clases (necesario en Kotlin)
    //testImplementation("org.mockito.inline:mockito-inline:5.2.0")
    // Mockito-Kotlin: Una capa encima de Mockito para que sea más natural en Kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    // Para testear Coroutines y Flows
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    // Para testear LiveData
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // --- INSTRUMENTED TESTING (Carpeta androidTest - Emulador) ---
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Mockito para Android (necesario para correr en el celular/emulador)
    androidTestImplementation("org.mockito:mockito-android:5.11.0")

    // --- HILT TESTING (Si quieres probar con inyección de dependencias) ---
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48")

    //kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.48")
    testImplementation(kotlin("test"))
}




/*
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.service)
    alias(libs.plugins.ksp)
    alias(libs.plugins.daggerHiltAndroid)
    //id("kotlin-kapt")
}

android {
    namespace = "com.practica.policeubgapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.practica.policeubgapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        //sourceCompatibility = JavaVersion.VERSION_11
        //targetCompatibility = JavaVersion.VERSION_11
        //jvmTarget.set(JvmTarget.JVM_1_8)
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.animation.core.lint)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //coil
    implementation(libs.coil.compose)

    //firebase
    implementation(platform(libs.firebase))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.google.play.service)


    //hilt y dagger
    implementation(libs.google.dagger.hilt.android)
    ksp(libs.google.dagger.hilt.android.compiler)
    //implementation(libs.google.dagger.hilt.android.testing)
    implementation(libs.androidx.hilt.navigation.compose)
}

 */