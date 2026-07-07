pluginManagement {
    val springDependencyManagementVersion: String by settings
    val springBootVersion: String by settings
    val avroGeneratorPluginVersion: String by settings
    val lombokVersion: String by settings


    plugins {
        id("io.spring.dependency-management") version springDependencyManagementVersion
        id("org.springframework.boot") version springBootVersion
        id("io.freefair.lombok") version lombokVersion
        id("com.github.davidmc24.gradle.plugin.avro") version avroGeneratorPluginVersion
    }
}

rootProject.name = "device-collector-service"