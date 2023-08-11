plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "id.my.davezy.todoapps"
  compileSdk = 33

  defaultConfig {
    applicationId = "id.my.davezy.todoapps"
    minSdk = 24
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    viewBinding = true
  }
}

dependencies {

  implementation(project(mapOf("path" to ":domain")))
  implementation(project(mapOf("path" to ":data")))

  val coroutinesVersion = "1.6.4"
  val lifecycleKtxVersion = "2.6.1"
  val fragmentKtxVersion = "1.6.0"

  // Hilt
  implementation("com.google.dagger:hilt-android:2.47")
  kapt("com.google.dagger:hilt-compiler:2.47")

  // Coroutines
  api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
  api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

  // LifeCycle
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleKtxVersion")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleKtxVersion")

  // Fragment - KTX
  implementation("androidx.fragment:fragment-ktx:$fragmentKtxVersion")

  implementation("androidx.core:core-ktx:1.10.1")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.9.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}