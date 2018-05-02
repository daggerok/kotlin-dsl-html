package daggerok

import com.github.kittinunf.fuel.httpGet
import daggerok.extensions.html.plus.*
import kotlinx.coroutines.experimental.async
import org.hamcrest.CoreMatchers.*
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.test.*

class StatelessPlusTest {

  @Test fun `stateless unit testing`() {

    val actual = html {
      head {
        title { "Title!" }+
        meta("charset" to "utf-8")
      }+
      body { "hello, people!" }
    }

    val expected = "<!DOCTYPE html><html><head><title>Title!</title>" +
        "<meta charset='utf-8'/></head><body>hello, people!</body></html>"

    assertEquals(expected, actual)
  }

  @Test fun `PLUS integration testing`() {

    val port = Random(1000).nextInt(1000) + 8080
    async { main(arrayOf("$port")) }
    TimeUnit.SECONDS.sleep(1)

    // blocking mode
    val (_request, _response, result) = "http://127.0.0.1:$port/plus".httpGet().responseString()
    val (ok, error) = result

    assertNull(error, "unexpected error")
    assertNotNull(ok, "expecting OK")
    assertThat(ok, containsString("PLUS"))
    assertThat(ok, containsString("stateless"))

    TimeUnit.SECONDS.sleep(2)
  }
}
