plugins {
    id("physical-quantities.kotlin-conventions-base")
}

kotlin {
    sourceSets["test"].kotlin {
        srcDir("${rootProject.rootDir}/common/src/test/kotlin")
    }
}

dokka {
    basePublicationsDirectory.set(layout.buildDirectory.dir("docs"))

    dokkaPublications.html {
        outputDirectory.set(layout.buildDirectory.dir("docs/javadoc"))
    }
}
