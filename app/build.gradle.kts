plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    annotationProcessor("android.arch.persistence.room:compiler:1.1.1")
    implementation("android.arch.persistence.room:rxjava2:1.1.1")
    implementation("android.arch.persistence.room:testing:1.1.1")


    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.ext:junit-ktx:1.1.5")

    val room_version = "2.6.1"

    kapt("androidx.room:room-compiler:$room_version");


    implementation("androidx.room:room-runtime:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
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

    implementation("androidx.test:core:1.2.0")

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
    implementation("androidx.activity:activity-ktx:1.8.1")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.45")
    //kapt ("com.google.dagger:hilt-android-compiler:2.28-alpha")

    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    //kapt ("androidx.hilt:hilt-compiler:1.0.0-alpha02")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Local Unit Tests
    implementation("androidx.test:core:1.5.0")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("org.robolectric:robolectric:4.3.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    testImplementation("org.mockito:mockito-core:2.25.0")

    // Instrumented Unit Tests
    // androidTestImplementation ("com.linkedin.dexmaker:dexmaker-mockito:2.12.1")
    androidTestImplementation("com.linkedin.dexmaker:dexmaker-mockito:2.28.3")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("org.mockito:mockito-core:2.25.0")


}

