plugins {
    kotlin("jvm") version "1.9.24"
    id("io.qameta.allure") version "2.12.0"
}

group = "qa.reinaldo"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    // Appium
    implementation("io.appium:java-client:9.4.0")

    // Selenium
    implementation("org.seleniumhq.selenium:selenium-java:4.33.0")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // Allure
    testImplementation("io.qameta.allure:allure-junit5:2.25.0")
    implementation("org.aspectj:aspectjweaver:1.9.21")

    // JUnit Vintage (para compatibilidade com IntelliJ)
    testImplementation("org.junit.vintage:junit-vintage-engine:5.10.2")
}

tasks.test {
    useJUnitPlatform()

    // Configuração de paralelização (equivalente ao surefire)
    maxParallelForks = 3

    // Encoding
    systemProperty("file.encoding", "UTF-8")
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

tasks.compileTestKotlin {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

allure {
    adapter {
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set("2.25.0")
            }
        }
    }
}

// Configuração para recursos de teste
sourceSets {
    test {
        resources {
            srcDir("src/test/java/qa/reinaldo/")
        }
    }
}
