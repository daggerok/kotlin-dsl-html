package daggerok.extensions

/* internal API */

internal fun Array<out String>.toHTML(separator: String = "") =
    this.joinToString(separator) { it }

internal fun Array<out Pair<String, String>>.toAttributes() = this.toMap().toAttributes()

internal fun Map<String, String>.toAttributes() =
    if (this.isEmpty()) "" else " " + this.entries
        .joinToString(separator = " ") { """'${it.key}'='${it.value}'""" }

/* public API */

fun html(vararg content: String) = "<html>${content.toHTML()}</html>"
fun head(vararg content: String) = "<head>${content.toHTML()}</head>"
fun title(vararg content: String) = "<title>${content.toHTML()}</title>"
fun body(vararg content: String) = "<body>${content.toHTML()}</body>"
fun h1(vararg content: String) = "<h1>${content.toHTML()}</h1>"

fun div(vararg pairs: Pair<String, String> = arrayOf(),
        close: Boolean = false, selfClosing: Boolean = close,
        innerHTML: String = "",
        transform: (() -> String)? = null) =
    if (selfClosing) "<div${pairs.toAttributes()}/>"
    else "<div${pairs.toAttributes()}>${if (transform != null) transform() else innerHTML}</div>"

/* integration testing */

val simpleStupid: (String) -> Unit = {
  println(
      html(
          head(
              title("Hey")
          ),
          body(
              h1(it),
              div { it },
              div(selfClosing = true),
              div("a" to "bbb"),
              div("c" to "ddd", selfClosing = true),
              div("e" to "fff") {
                it
              },
              div {
                it
              },
              div(),
              div(close = true)
          )
      )
  )
}
