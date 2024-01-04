import java.lang.System.getProperty

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

   // id("kotlin-android-extensions")
    id("dagger.hilt.android.plugin")
   // id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    id("kotlin-android")
}

android {
    namespace = "com.fa.junittesting"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fa.junittesting"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        //buildConfigField("String", "API_KEY", "API_KEY")

        testInstrumentationRunner = "com.fa.junittesting.HiltTestRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("com.google.ar:core:1.41.0")
    implementation("android.arch.persistence.room:rxjava2:1.1.1")
    implementation("android.arch.persistence.room:testing:1.1.1")
    implementation("androidx.test:core:1.5.0")
    kapt ("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.ext:junit-ktx:1.1.5")

    val room_version = "2.6.1"

    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-runtime:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:$room_version")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$room_version")

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:$room_version")

    // optional - Test helpers

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")

    // Local Unit Tests
    testImplementation("androidx.room:room-testing:$room_version")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.truth:truth:1.0.1")

    // Instrumented Unit Tests
    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("com.google.truth:truth:1.0.1")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")

//******************************************************

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")


    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    //kapt ("com.github.bumptech.glide:compiler:4.11.0")

    // Activity KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.8.2")

    //kapt ("androidx.hilt:hilt-compiler:1.0.0-alpha02")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // DI with Hilt
    implementation ("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-compiler:2.48")


    // Local Unit Tests
    implementation("androidx.test:core:1.5.0")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("org.robolectric:robolectric:4.3.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("org.mockito:mockito-core:2.28.2")
    testImplementation ("com.google.dagger:hilt-android-testing:2.48")
    kaptTest ("com.google.dagger:hilt-compiler:2.48")


    // Instrumented Unit Tests
    // androidTestImplementation ("com.linkedin.dexmaker:dexmaker-mockito:2.12.1")
    androidTestImplementation("com.linkedin.dexmaker:dexmaker-mockito:2.28.3")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("org.mockito:mockito-core:2.28.2")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48")
    kaptAndroidTest ("com.google.dagger:hilt-compiler:2.48")
    debugImplementation("androidx.fragment:fragment-testing:1.6.2")


// Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
// Retrofit with Scalar Converter
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

}

// Allow references to generated code

kapt {
    correctErrorTypes = true
}
