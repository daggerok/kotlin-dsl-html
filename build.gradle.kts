import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val kotlinVersion: String by extra
val javaVersion: String by extra

buildscript {

  var kotlinVersion: String by extra
  kotlinVersion = "1.2.31"

  var javaVersion: String by extra
  javaVersion = "1.8"

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
  mainClassName = "daggerok.App"
}

dependencies {
  compile(kotlin("stdlib"))
  testCompile("junit:junit:4.12")
  allprojects.forEach {
    archives(it)
  }
  compile(kotlinModule("stdlib-jdk8", kotlinVersion))
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
