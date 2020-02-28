plugins {
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
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
    implementation("com.github.kittinunf.fuel:fuel:2.2.1")
    implementation("com.jayway.jsonpath:json-path:2.4.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.2.4.RELEASE")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
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
    val integrationTest by creating {
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
    val integrationTestImplementation by getting {
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
    register<Test>("runIntegrationTests") {
        group = JavaBasePlugin.VERIFICATION_GROUP
        description = "Runs integration tests only"
        testClassesDirs = sourceSets["integrationTest"].output.classesDirs
        classpath = sourceSets["integrationTest"].runtimeClasspath
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
