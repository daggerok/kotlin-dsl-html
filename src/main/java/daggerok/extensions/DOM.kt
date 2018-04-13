@file:JvmName("DOM")

package daggerok.extensions

// flatten Element.attributes
fun Array<out Pair<Any, Any>>.join(quote: String = "'", separator: String = " ") =
    if (this.isEmpty()) "" else " " + this.map { "${it.first}=${quote + it.second + quote}" }
        .joinToString(separator) { it }

/* DOM API */

fun html(vararg attributes: Pair<String, String> = arrayOf(),
         content: String = "",
         func: (String) -> String) = "<html${attributes.join()}>${func(content.trimIndent())}</html>"

fun head(vararg attributes: Pair<String, String> = arrayOf(),
         content: String = "",
         func: (String) -> String) = "<head${attributes.join()}>${func(content.trimIndent())}</head>"

fun title(vararg attributes: Pair<String, String> = arrayOf(),
          content: String = "",
          func: (String) -> String) = "<title${attributes.join()}>${func(content.trimIndent())}</title>"

fun body(vararg attributes: Pair<String, String> = arrayOf(),
         content: String = "",
         func: (String) -> String) = "<body${attributes.join()}>${func(content.trimIndent())}</body>"

fun div(vararg attributes: Pair<String, String> = arrayOf(),
        content: String = "", // content: Array<String> = arrayOf(), //vararg content: String = arrayOf(),
        func: (String) -> String) = "<div${attributes.join()}>${func(content.trimIndent())}</div>"
// ...
