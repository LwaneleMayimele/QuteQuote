import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import za.co.wethinkcode.project.TestableWebbyHelloWorld;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebbyHelloWorldTest {

    @Test
    @DisplayName("GET /hello")
    public void shouldGetHelloWorld() {
        TestableWebbyHelloWorld api = new TestableWebbyHelloWorld();
        api.start();
        HttpResponse<String> response = Unirest.get("http://localhost:7000/hello").asString();
        assertEquals(200, response.getStatus());
        assertEquals("Hello, world!", response.getBody());
        api.stop();
    }
}