
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
    implementation(libs.androidx.biometric.ktx)
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

    //biometria
    implementation("androidx.biometric:biometric:1.2.0-alpha05")
}
