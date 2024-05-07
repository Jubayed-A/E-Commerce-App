plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.jubayedalam.e_commerce_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jubayedalam.e_commerce_app"
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
        //noinspection DataBindingWithoutKapt
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    // old plugin

/*    // indicator
    implementation("me.relex:circleindicator:2.1.6")

    // for meow bottom navigation
    implementation("com.github.qamarelsafadi:CurvedBottomNavigation:0.1.3")

    // for range seekbar
    implementation ("com.github.MohammedAlaaMorsi:RangeSeekBar:1.0.6")

    // Circular image
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // image slider
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.2")

    // for stepper
    implementation("com.github.badoualy:stepper-indicator:1.0.7")*/



    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // swipe delete
    implementation ("it.xabaras.android:recyclerview-swipedecorator:1.4")


    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // for range seekbar
    implementation ("com.github.MohammedAlaaMorsi:RangeSeekBar:1.0.6")

    // Circular image
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // bottom navigation
    implementation("com.github.armcha:SpaceNavigationView:1.6.0")

    // for stepper
    implementation("com.github.badoualy:stepper-indicator:1.0.7")

    // image slider
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.2")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.13.0")

    // for image picker
    implementation("com.github.dhaval2404:imagepicker:2.1")

    // for swipe to delete
    implementation("com.chauthai.swipereveallayout:swipe-reveal-layout:1.4.1")

    // for shimmer effect
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // Room components
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")

    // Room Kotlin extensions
    implementation ("androidx.room:room-ktx:2.6.1")


}