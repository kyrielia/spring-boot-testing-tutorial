import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
    kotlin("plugin.jpa") version "1.3.72"
}

group = "com.kyri"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot-starter-web:2.2.4.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.2.4.RELEASE")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
    implementation("com.github.kittinunf.fuel:fuel:2.2.1")
    implementation("com.jayway.jsonpath:json-path:2.4.0")

    runtime("com.h2database:h2:1.4.199")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.2.4.RELEASE")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

sourceSets {
    val unitTest by creating {
        compileClasspath += sourceSets["main"].output + sourceSets["test"].output
        runtimeClasspath += sourceSets["main"].output + sourceSets["test"].output
    }
    val applicationTest by creating {
        compileClasspath += sourceSets["main"].output + sourceSets["test"].output
        runtimeClasspath += sourceSets["main"].output + sourceSets["test"].output
    }
    val contractTest by creating {
        compileClasspath += sourceSets["main"].output + sourceSets["test"].output
        runtimeClasspath += sourceSets["main"].output + sourceSets["test"].output
    }
    val e2eTest by creating {
        compileClasspath += sourceSets["main"].output + sourceSets["test"].output
        runtimeClasspath += sourceSets["main"].output + sourceSets["test"].output
    }
}

configurations {
    val unitTestImplementation by getting {
        extendsFrom(configurations["testImplementation"])
        extendsFrom(configurations["testRuntime"])
    }
    val applicationTestImplementation by getting {
        extendsFrom(configurations["testImplementation"])
        extendsFrom(configurations["testRuntime"])
    }
    val contractTestImplementation by getting {
        extendsFrom(configurations["testImplementation"])
        extendsFrom(configurations["testRuntime"])
    }
    val e2eTestImplementation by getting {
        extendsFrom(configurations["testImplementation"])
        extendsFrom(configurations["testRuntime"])
    }
}

tasks{
    register<Test>("runUnitTests") {
        group = JavaBasePlugin.VERIFICATION_GROUP
        description = "Runs unit tests only"
        testClassesDirs = sourceSets["unitTest"].output.classesDirs
        classpath = sourceSets["unitTest"].runtimeClasspath
    }
    register<Test>("runApplicationTests") {
        group = JavaBasePlugin.VERIFICATION_GROUP
        description = "Runs application tests only"
        testClassesDirs = sourceSets["applicationTest"].output.classesDirs
        classpath = sourceSets["applicationTest"].runtimeClasspath
    }
    register<Test>("runE2ETests") {
        group = JavaBasePlugin.VERIFICATION_GROUP
        description = "Runs integration tests only"
        testClassesDirs = sourceSets["e2eTest"].output.classesDirs
        classpath = sourceSets["e2eTest"].runtimeClasspath
    }
    register<Test>("runContractTests") {
        group = JavaBasePlugin.VERIFICATION_GROUP
        description = "Runs contract tests only"
        testClassesDirs = sourceSets["contractTest"].output.classesDirs
        classpath = sourceSets["contractTest"].runtimeClasspath
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
