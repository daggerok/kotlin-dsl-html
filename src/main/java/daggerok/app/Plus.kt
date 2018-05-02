package daggerok.app

import daggerok.extensions.html.plus.*

fun plus() = html("lang" to "en", "ng-app" to "my-app") {
  head {
    title { "PLUS" }
  } +
  body {
    "Okay..&nbsp;" +
    div("class" to "container-fluid") {
      "some text:" +
      div { "yeah!" } +
      "more text"
    } +
    div { "stateless" }
  }
}
