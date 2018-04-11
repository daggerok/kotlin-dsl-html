package daggerok

import daggerok.extensions.*

fun main(args: Array<String>) {
  println(
    html("lang" to "en", "ng-app" to "my-app") {
      head {
        title {
          "Kotlin awesome!"
        }
      } +
      body {
        div("class" to "container-fluid") {
          "DSL as 1-2-3"
        }
      }
    }
  )
}
