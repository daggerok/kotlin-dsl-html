package daggerok

import daggerok.extensions.*
import io.ktor.application.call
import io.ktor.http.ContentType.Text.Html
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
  val port = if (args.isEmpty()) 8080 else try { args[0].toInt() } catch (e: Throwable) { 8080 }
  embeddedServer(Netty, port) {
    routing {
      get("") {
        call.respondText(status = OK, contentType = Html) {
          html("lang" to "en", "ng-app" to "my-app") {
            head {
              title { "ololo" }
            } +
            body {
              "Okay..&nbsp;" +
              div("class" to "container-fluid") {
                "some text:" +
                div { "yeah!" } +
                "more text"
              } +
              div { "hey!" }
            }
          }
        }
      }
    }
  }.start(wait = true)
}
