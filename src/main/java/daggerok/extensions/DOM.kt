@file:JvmName("DOM")

package daggerok.extensions

// flatten Element.attributes
fun Array<out Pair<String, String>>.join(quote: String = "'") = if (this.isEmpty()) ""
  else " " + this.map { "${it.first}=${quote + it.second + quote}" }
    .joinToString(" ") { it }

// collect element content into innerHTML
fun Array<out String>.join(separator: String = "") = if (this.isEmpty()) ""
  else this.joinToString(separator) { it }

// HTML content receiver (appender) builder
class HtmlBuilder {
  var innerHTML: String = "<!DOCTYPE html>"
  //  private set
  fun text(content: String) {
    innerHTML += content
  }
}

/* DOM API */

fun html(vararg attributes: Pair<String, String> = arrayOf(),
         content: Array<String> = arrayOf(),
         func: HtmlBuilder.(String) -> Unit): String {

  val context = HtmlBuilder()
  context.text("<html${attributes.join()}>")
  context.func(content.join())
  context.text("</html>")
  return context.innerHTML
}

fun HtmlBuilder.head(vararg attributes: Pair<String, String> = arrayOf(),
                     content: Array<String> = arrayOf(),
                     func: HtmlBuilder.(String) -> Unit) {

  text("<head${attributes.join()}>")
  func(content.join())
  text("</head>")
}

fun HtmlBuilder.title(vararg attributes: Pair<String, String> = arrayOf(),
                      content: Array<String> = arrayOf(),
                      func: HtmlBuilder.(String) -> Unit) {

  text("<title${attributes.join()}>")
  func(content.join())
  text("</title>")
}

fun HtmlBuilder.body(vararg attributes: Pair<String, String> = arrayOf(),
                     content: Array<String> = arrayOf(),
                     func: HtmlBuilder.(String) -> Unit) {

  text("<body${attributes.join()}>")
  func(content.join())
  text("</body>")
}

fun HtmlBuilder.div(vararg attributes: Pair<String, String> = arrayOf(),
                    content: Array<String> = arrayOf(), //vararg content: String = arrayOf(),
                    func: HtmlBuilder.(String) -> Unit) {

  text("<div${attributes.join()}>")
  func(content.join())
  text("</div>")
}

// ...
