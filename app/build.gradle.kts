import org.jetbrains.kotlin.fir.expressions.FirEmptyArgumentList.arguments
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("com.google.devtools.ksp")
} */

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("com.google.devtools.ksp")
    id("org.jetbrains.dokka")
}

android {
    namespace = "com.example.affirmations"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.affirmations"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }

    }
}


    dependencies {

        //room
        //room
        //room
        val room_version = "2.5.0"
        implementation("androidx.room:room-runtime:$room_version")
        // optional - Kotlin Extensions and Coroutines support for Room
        implementation("androidx.room:room-ktx:$room_version")
        // To use Kotlin Symbol Processing (KSP)
        ksp("androidx.room:room-compiler:$room_version")

        // optional - Test helpers
        testImplementation("androidx.room:room-testing:$room_version")
        annotationProcessor("androidx.room:room-compiler:$room_version")

        // Compose dependencies using the BoM for consistent versions
        implementation("androidx.navigation:navigation-compose:2.4.1")
        implementation(platform("androidx.compose:compose-bom:2023.05.01"))
        implementation("androidx.activity:activity-compose:1.7.2")
        implementation("androidx.compose.material3:material3")
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.core:core-ktx:1.10.1")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
        // ViewModel support for Compose
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")

        implementation("androidx.appcompat:appcompat:1.6.1")

        //dokka
        //implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.4.30")

        // Testing dependencies
        debugImplementation("androidx.compose.ui:ui-test-manifest")
        debugImplementation("androidx.compose.ui:ui-tooling")

        // Retrofit
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
// Retrofit with Scalar Converter
        implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
        implementation("com.squareup.okhttp3:okhttp:4.11.0")

        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

        //local tests
        testImplementation("junit:junit:4.13.2")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")


        androidTestImplementation("androidx.compose.ui:ui-test-junit4-android:1.5.1")
        androidTestImplementation("androidx.navigation:navigation-testing:2.4.1")

        //mockito
        androidTestImplementation("org.mockito:mockito-android:4.6.1")

        //espresso
        androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
        //androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.0")



        //implementation("androidx.navigation:navigation-testing:2.6.1")

        // Coil (display images)
        implementation("io.coil-kt:coil-compose:2.4.0")



    }



