package daggerok.app

import daggerok.extensions.html.dom.*

fun dom() = html("lang" to "en", "ng-app" to "my-app") {
  head {
    title { text("DOM") }
  }
  body {
    innerHTML += "Okay..&nbsp;"
    div("class" to "container-fluid") {
      innerHTML += "some text:"
      div { text("yeah!") }
      innerHTML += "more text"
    }
    div { text("stateful") }
  }
}
