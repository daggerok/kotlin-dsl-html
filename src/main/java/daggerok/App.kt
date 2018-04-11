package daggerok

import daggerok.extensions.*

fun main(args: Array<String>) {
  println(
    html("lang" to "en", "ng-app" to "my-app") {
      head {
        title {
          text("Kotlin awesome!")
        }
      }
      body {
        div("class" to "container-fluid") {
          innerHTML += "DSL as 1-2-3"
        }
      }
    }
  )
}
