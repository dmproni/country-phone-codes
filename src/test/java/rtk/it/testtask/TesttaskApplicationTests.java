package rtk.it.testtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class TesttaskApplicationTests {
	@Value("${baseUrl}")
	String baseUrl;
	@Value("${countryNamesJson}")
	String countryNamesJson;
	@Value("${phoneCodesJson}")
	String phoneCodesJson;

	@Test
	void contextLoads() {
		System.out.println("good");
		WebClient webClient = WebClient.create(baseUrl);

		final Flux<Country> result =
				webClient.get().uri("/{name}", countryNamesJson)
				.retrieve().bodyToFlux(Country.class);

		result.subscribe(
				value -> System.out.println(value),
				error -> error.printStackTrace(),
				() -> System.out.println("default")
		);
	}

}
