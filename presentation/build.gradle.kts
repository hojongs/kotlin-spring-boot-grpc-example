import com.google.protobuf.gradle.*

plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
    id("com.google.protobuf")
}

dependencies {
    implementation(project(":service"))
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.github.lognet:grpc-spring-boot-starter:3.5.1")
    implementation("com.salesforce.servicelibs:reactor-grpc-stub:1.0.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.10.0"
    }

    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.25.0"
        }
        id("reactor") {
            artifact = "com.salesforce.servicelibs:reactor-grpc:1.0.0"
        }
    }

    generateProtoTasks {
        ofSourceSet("main").forEach { generateProtoTask ->
            generateProtoTask
                .plugins {
                    id("grpc")
                    id("reactor")
                }
        }
    }
}
