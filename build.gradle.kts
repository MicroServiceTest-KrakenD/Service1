import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("kapt") version "1.6.10"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-parent:2.0.1.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:2.0.1.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2:2.1.214")

    val configurationProcessor ="org.springframework.boot:spring-boot-configuration-processor"
    kapt(configurationProcessor)
    kaptTest(configurationProcessor)
    annotationProcessor(configurationProcessor)
}

kapt {
    annotationProcessor("org.springframework.boot.configurationprocessor.ConfigurationMetadataAnnotationProcessor")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
