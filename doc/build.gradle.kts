
plugins {
    java
    kotlin("jvm")
    id("org.jmailen.kotlinter")
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") } // used by kotlin4example
}

dependencies {
    testImplementation(project(":big-decimal"))
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.25.1")
    testImplementation("com.github.jillesvangurp:kotlin4example:1.1.6")
}
