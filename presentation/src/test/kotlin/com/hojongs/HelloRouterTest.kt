package com.hojongs

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient
class HelloRouterTest {

    @Autowired
	private lateinit var client: WebTestClient

	@Test
	fun `test hello()`() {
		client.get()
			.uri("/hello?")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk
			.expectBody()
			.jsonPath("$.msg")
			.isEqualTo("hello")
	}
}
