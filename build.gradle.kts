import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

version = "0.1.0-SNAPSHOT"
group = "com.danix"

buildscript {
    val kotlinVersion = "1.1.0-beta-38"
    val springBootVersion = "2.0.0.BUILD-SNAPSHOT"
    extra["kotlinVersion"] = kotlinVersion
    extra["springBootVersion"] = springBootVersion

    repositories {
        mavenCentral()
        maven { setUrl("https://plugins.gradle.org/m2/") }
        maven { setUrl("http://dl.bintray.com/kotlin/kotlin-eap-1.1") }
        maven { setUrl("https://repo.spring.io/snapshot") }
        maven { setUrl("https://repo.spring.io/milestone") }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    }
}

apply {
    plugin("idea")
    plugin("kotlin")
    plugin("kotlin-noarg")
    plugin("kotlin-spring")
    plugin("org.springframework.boot")
}

repositories {
    mavenCentral()
    maven { setUrl("https://dl.bintray.com/jetbrains/spek") }
    maven { setUrl("http://dl.bintray.com/kotlin/kotlin-eap-1.1") }
    maven { setUrl("https://repo.spring.io/milestone") }
    maven { setUrl("https://repo.spring.io/snapshot") }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

val kotlinVersion = extra["kotlinVersion"] as String
val springDataVersion = "2.0.0.BUILD-SNAPSHOT"

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    compile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    compile("org.springframework.boot.experimental:spring-boot-starter-web-reactive:0.1.0.BUILD-SNAPSHOT") {
        exclude(module= "spring-boot-starter-tomcat")
        exclude(module= "hibernate-validator")
    }
    compile("org.springframework.boot:spring-boot-devtools")
    testCompile("org.springframework.boot:spring-boot-starter-test")

    compile("com.samskivert:jmustache:1.13")
    compile("com.atlassian.commonmark:commonmark:0.8.0")

    compile("io.projectreactor:reactor-core")
    compile("io.projectreactor.ipc:reactor-netty")
    compile("io.projectreactor:reactor-kotlin:1.0.0.BUILD-SNAPSHOT")
    testCompile("io.projectreactor.addons:reactor-test")
    testCompile("com.nhaarman:mockito-kotlin:1.2.0")
    testCompile("com.natpryce:hamkrest:1.2.3.0")


    compile("io.undertow:undertow-core:1.3.17.Final")

    compile("com.fasterxml.jackson.core:jackson-databind")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    compile("commons-logging:commons-logging:1.2")
    compile("org.slf4j:slf4j-api:1.7.21")
    compile("ch.qos.logback:logback-classic:1.1.7")
}
