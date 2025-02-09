plugins {
    id("physical-quantities.kotlin-conventions-base")
}

kotlin {
    sourceSets["main"].kotlin {
        srcDir("${rootProject.rootDir}/double/src/main/kotlin")
    }
}

dependencies {
    testImplementation("com.github.jillesvangurp:kotlin4example:1.1.6")
}

repositories {
    maven { url = uri("https://jitpack.io") } // used by kotlin4example
}
