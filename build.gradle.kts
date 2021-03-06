import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.config.AnalysisFlag.Flags.experimental
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.dsl.ExperimentalExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

val javaVersion = "1.8"
val lombokVersion = "1.16.20"

buildscript {

  var kotlinVersion: String by extra
  kotlinVersion = "1.2.41"

  repositories {
    mavenLocal()
    mavenCentral()
    maven { setUrl("https://plugins.gradle.org/m2/") }
    jcenter()
  }
  dependencies {
    classpath(kotlin("gradle-plugin", kotlinVersion))
    classpath("com.github.jengelman.gradle.plugins:shadow:2.0.0")
  }
}

plugins {
  java
  idea
  eclipse
  application
  kotlin("jvm") version "1.2.41" apply true
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

apply {
  plugin("kotlin")
}

kotlin {
  experimental.coroutines = Coroutines.ENABLE
}

application {
  mainClassName = "daggerok.AppKt"
}

repositories {
  maven { setUrl("https://plugins.gradle.org/m2/") }
  maven { setUrl("https://dl.bintray.com/kotlin/ktor/") }
  mavenCentral() // TODO: wait for maven central sync
//  jcenter()
}

dependencies {

  allprojects.forEach {
    archives(it)
  }

  implementation( "com.github.daggerok:kotlin-html-dsl:1.0.ALL")

  compile(kotlin("stdlib"))
  compile(kotlin("stdlib-jdk8"))
  compile( "io.ktor:ktor-server-netty:0.9.1")
  compile( "ch.qos.logback:logback-classic:1.2.1")

  testCompile(kotlin("test"))
  testCompile(kotlin("test-junit"))
  testCompile("junit:junit:4.12")
  testCompile( "com.github.kittinunf.fuel:fuel:1.12.1")
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

val wrapper: Wrapper by tasks
wrapper.gradleVersion = "4.7"
wrapper.distributionType = Wrapper.DistributionType.ALL
