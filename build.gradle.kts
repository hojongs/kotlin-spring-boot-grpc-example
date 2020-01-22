import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.61"
    idea
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("idea")
    }

    group = "com.hojongs"
    version = "1.0-SNAPSHOT"

    java.sourceCompatibility = JavaVersion.VERSION_1_8

    dependencies {
        implementation(kotlin("kotlin-reflect"))
        implementation(kotlin("stdlib-jdk8"))
    }

    tasks {
        withType<Test> {
            useJUnitPlatform()
        }

        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
        }
    }
}
