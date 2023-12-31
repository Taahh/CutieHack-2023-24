plugins {
    id("java")
    id("org.springframework.boot") version "3.1.5"
}

apply(plugin = "io.spring.dependency-management")

group = "dev.taah"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.json:json:20231013")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.google.guava:guava:32.1.3-jre")
    implementation("org.xerial:sqlite-jdbc:3.7.2")
}

tasks.test {
    useJUnitPlatform()
}