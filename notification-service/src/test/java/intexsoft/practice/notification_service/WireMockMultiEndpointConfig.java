package intexsoft.practice.notification_service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@TestConfiguration
public class WireMockMultiEndpointConfig {

    private static final int WIREMOCK_PORT = 9561;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        WireMockServer wireMockServer = new WireMockServer(options().port(WIREMOCK_PORT));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/users/.*"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("{\"email\": \"mocked.user@example.com\"}")));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/rooms/.*"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(StubDataFactory.createRoomJson())));

        return wireMockServer;
    }

    @Bean(name = "userClientBaseUrl")
    public String userClientBaseUrl() {
        return "http://localhost:" + WIREMOCK_PORT + "/api/users";
    }

    @Bean(name = "roomClientBaseUrl")
    public String roomClientBaseUrl() {
        return "http://localhost:" + WIREMOCK_PORT + "/api/rooms";
    }
}