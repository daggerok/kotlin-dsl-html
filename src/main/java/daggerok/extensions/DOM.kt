@file:JvmName("DOM")

package daggerok.extensions

// flatten Element.attributes
fun Array<out Pair<String, String>>.join(quote: String = "'") = if (this.isEmpty()) ""
  else " " + this.map { "${it.first}=${quote + it.second + quote}" }
    .joinToString(" ") { it }

// collect element content into innerHTML
fun Array<out String>.join(separator: String = "") = if (this.isEmpty()) ""
  else this.joinToString(separator) { it }

/* DOM API */

fun html(vararg attributes: Pair<String, String> = arrayOf(),
         content: Array<String> = arrayOf(),
         func: (String) -> String) = "<html${attributes.join()}>${func(content.join())}</html>"

fun head(vararg attributes: Pair<String, String> = arrayOf(),
         content: Array<String> = arrayOf(),
         func: (String) -> String) = "<head${attributes.join()}>${func(content.join())}</head>"

fun title(vararg attributes: Pair<String, String> = arrayOf(),
          content: Array<String> = arrayOf(),
          func: (String) -> String) = "<title${attributes.join()}>${func(content.join())}</title>"

fun body(vararg attributes: Pair<String, String> = arrayOf(),
         content: Array<String> = arrayOf(),
         func: (String) -> String) = "<body${attributes.join()}>${func(content.join())}</body>"

fun div(vararg attributes: Pair<String, String> = arrayOf(),
        content: Array<String> = arrayOf(), //vararg content: String = arrayOf(),
        func: (String) -> String) = "<div${attributes.join()}>${func(content.join())}</div>"
// ...
