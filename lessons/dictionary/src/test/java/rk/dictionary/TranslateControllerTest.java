package rk.dictionary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TranslateControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void greetingShouldReturnRandomWord() {
       String result = testRestTemplate.getForObject("http://localhost:"+port+"/translation/random", String.class);
        assertThat(result).isNotNull().isNotEmpty();
    }
}
