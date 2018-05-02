package daggerok

import daggerok.app.dom
import daggerok.app.plus
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
      get("/dom") {
        call.respondText(status = OK, contentType = Html) {
          dom()
        }
      }
      get("/plus") {
        call.respondText(status = OK, contentType = Html) {
          plus()
        }
      }
    }
  }.start(wait = true)
}
