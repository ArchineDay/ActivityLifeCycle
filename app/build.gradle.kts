plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.activityjump"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.activityjump"
        minSdk = 24
        targetSdk = 31
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.zhihu.android:matisse:0.5.3-beta3")
    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("androidx.test.ext:junit:1.1.5")
    implementation("com.blankj:utilcodex:1.31.1")
    implementation("jp.wasabeef:blurry:4.0.1")
    implementation("com.github.mmin18:realtimeblurview:1.2.1")
    implementation("io.alterac.blurkit:blurkit:1.1.0")

}