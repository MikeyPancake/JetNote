plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //id("kotlin-kapt")
    //kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") // KSP is replacing Kapt
}


android {
    namespace = "com.udemy.jetnote"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.udemy.jetnote"
        minSdk = 31
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}


dependencies {


    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // View Model dependency
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    //Room
    val room_version = "2.6.1"
    implementation ("androidx.room:room-runtime:$room_version")
    annotationProcessor ("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt) MUST HAVE!
    //kapt("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")

    // To use Kotlin annotation processing tool (KSP) which is replacing Kapt
    ksp("androidx.room:room-compiler:$room_version")

    //dagger-hilt
    val daggerVersion = "2.50"  //2.39 in course
    implementation("com.google.dagger:hilt-android-gradle-plugin:${daggerVersion}")
    implementation("com.google.dagger:hilt-android:${daggerVersion}")
    //kapt("com.google.dagger:hilt-compiler:${daggerVersion}")
    //kapt("androidx.hilt:hilt-compiler:1.1.0") // 1.0.0 in course
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0") //1.0.0-alpha03 in course
    ksp("com.google.dagger:hilt-compiler:${daggerVersion}")
    ksp("androidx.hilt:hilt-compiler:1.1.0") // 1.0.0 in course

    // coroutines
    val coroutinesVersion = "1.7.3" // 1.5.2 in course
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${coroutinesVersion}")
}
