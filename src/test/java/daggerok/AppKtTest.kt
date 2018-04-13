package daggerok

import com.github.kittinunf.fuel.httpGet
import kotlinx.coroutines.experimental.async
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.test.*

class AppKtTest {

  @Test
  fun integrationTesting() {

    val port = Random(1000).nextInt(1000) + 8080
    async { main(arrayOf("$port")) }
    TimeUnit.SECONDS.sleep(1)

    // blocking mode
    val (request, response, result) = "http://127.0.0.1:$port".httpGet().responseString()
    val (ok, error) = result

    assertNull(error, "unexpected error")
    assertNotNull(ok, "expecting OK")
    assertThat(ok, containsString("ololo"))
    assertThat(ok, containsString("some text"))
    assertThat(ok, containsString("more text"))

    TimeUnit.SECONDS.sleep(2)
  }
}
