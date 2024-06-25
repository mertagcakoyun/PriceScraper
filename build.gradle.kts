plugins {
    id("java")
    id("org.springframework.boot") version "2.6.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "com.xprice"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jsoup:jsoup:1.14.3")
    implementation("com.google.guava:guava:32.0.1-jre")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("com.github.ben-manes.caffeine:caffeine:3.0.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jsoup:jsoup:1.14.3")
    testImplementation ("junit:junit:4.13.2")
}

tasks.test {
    useJUnitPlatform()
}
