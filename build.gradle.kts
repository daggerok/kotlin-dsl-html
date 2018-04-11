import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val javaVersion = "1.8"
val lombokVersion = "1.16.20"
val kotlinVersion: String by extra

buildscript {

  var kotlinVersion: String by extra
  kotlinVersion = "1.2.31"

  repositories {
    mavenLocal()
    mavenCentral()
    maven {
      setUrl("https://plugins.gradle.org/m2/")
    }
    jcenter()
  }
  dependencies {
    classpath(kotlin("gradle-plugin", kotlinVersion))
    classpath("com.github.jengelman.gradle.plugins:shadow:2.0.0")
  }
}

plugins {
  java
  application
  kotlin("jvm") version "1.2.31" apply true
}

apply {
  plugin("kotlin")
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

application {
  mainClassName = "daggerok.AppKt"
}

dependencies {

  allprojects.forEach {
    archives(it)
  }

  compile(kotlin("stdlib"))
  compile(kotlin("stdlib-jdk8", kotlinVersion))
  compileOnly(module("org.projectlombok", "lombok", lombokVersion))

  testCompileOnly(module("org.projectlombok", "lombok", lombokVersion))
  testCompile("junit:junit:4.12")
}

repositories {
  mavenLocal()
  mavenCentral()
  maven {
    setUrl("https://plugins.gradle.org/m2/")
  }
  jcenter()
}

defaultTasks("clean", "installDist")

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  jvmTarget = javaVersion
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
  jvmTarget = javaVersion
}
