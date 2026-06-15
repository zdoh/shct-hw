import com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask

plugins {
	java
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("com.github.davidmc24.gradle.plugin.avro")
    id("io.freefair.lombok")
}

group = "ru.zdoher"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

repositories {
	mavenCentral()
	maven {
		url = uri("https://packages.confluent.io/maven/")
	}
}

sourceSets {
    main {
        java {
            srcDirs("${buildDir}/generated-sources")
        }
    }
}

dependencies {
	val clickHouseJdbc: String by project
	val flywayDatabaseClickhouse: String by project
	val avroVersion: String by project
	val vavrVersion: String by project
	val schemaRegistryClientVersion: String by project
	val mapstructVersion: String by project
	val dotenvJava: String by project

	implementation("org.springframework.boot:spring-boot-starter-kafka")
	implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.integration:spring-integration-redis")

	implementation("io.confluent:kafka-schema-registry-client:$schemaRegistryClientVersion")
    implementation("io.confluent:kafka-avro-serializer:$schemaRegistryClientVersion")

    implementation("org.apache.avro:avro:$avroVersion")

	implementation("com.clickhouse:clickhouse-jdbc:$clickHouseJdbc")
	implementation("org.springframework.boot:spring-boot-starter-flyway")
	implementation("org.flywaydb:flyway-database-clickhouse:$flywayDatabaseClickhouse")

    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")

    implementation("io.github.cdimascio:dotenv-java:$dotenvJava")

    // Mapping
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

    // Metrics
    implementation("io.micrometer:micrometer-registry-prometheus")

    // Annotation processors (lombok, spring-boot-configuration)
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

	// Vavr to be more functional
	implementation("io.vavr:vavr:$vavrVersion")

	testImplementation("org.springframework.boot:spring-boot-starter-kafka-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

configurations.all {
    resolutionStrategy {
        capabilitiesResolution {
            withCapability("org.lz4:lz4-java") {
                selectHighestVersion()
            }
        }
    }
}

val generateAvro = tasks.register<GenerateAvroJavaTask>("generateAvro") {
    source(file("src/main/resources/avro"))
    setOutputDir(file("build/generated-sources/avro"))
}

tasks.compileJava {
    dependsOn(generateAvro)
    source(generateAvro)
}

tasks.withType<Test> {
	useJUnitPlatform()
}

