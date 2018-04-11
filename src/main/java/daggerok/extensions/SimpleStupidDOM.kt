package daggerok.extensions

fun html(vararg content: String) = "<html>${content.joinToString("") { it }}content</html>"

fun head(vararg content: String) = "<head>${content.joinToString("") { it }}</head>"
fun title(vararg content: String) = "<title>${content.joinToString("") { it }}</title>"

fun body(vararg content: String) = "<body>${content.joinToString("") { it }}</body>"
fun div(vararg content: String) = "<div>${content.joinToString("") { it }}</div>"

val simpleStupid: (String) -> Unit = {
  println(
      html(
          head(
              title("Hey")
          ),
          body(
              div(it)
          )
      )
  )
}
