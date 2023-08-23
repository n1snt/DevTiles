plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dev.tiles"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.dev.tiles"
        minSdk = 24
        targetSdk = 33

        // The version number is of the form:
        // <major>.<minor>.<maintenance>[dev|alpha<build>|beta<build>|]
        // The <build> is only present for alpha and beta releases (e.g., 2.0.4alpha2 or 2.0.4beta4), developer builds do
        // not have a build number (e.g., 2.0.4dev) and official releases only have three components (e.g., 2.0.4).
        //
        // The version code is derived from the version name as follows:
        // AbbCCtDD
        // A: 1-digit decimal number representing the major version
        // bb: 2-digit decimal number representing the minor version
        // CC: 2-digit decimal number representing the maintenance version
        // t: 1-digit decimal number representing the type of the build
        // 0: developer build
        // 1: alpha release
        // 2: beta release
        // 3: public release
        // DD: 2-digit decimal number representing the build
        // 00 for internal builds and public releases
        // alpha/beta build number for alpha/beta releases
        //
        // This ensures the correct ordering between the various types of releases (dev < alpha < beta < release) which is
        // needed for upgrades to be offered correctly.
        versionCode = 1000003
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation("androidx.navigation:navigation-compose:2.6.0")
}