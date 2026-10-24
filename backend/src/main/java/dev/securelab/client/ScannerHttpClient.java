package dev.securelab.client;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

@Component // componente generico
public class ScannerHttpClient
{
    private static final Map<String, URI> ALLOWED_TARGETS = Map.of(
            "minidocs", URI.create("http://127.0.0.1:8081")
    );

    private final RestClient restClient;

    public ScannerHttpClient(RestClient.Builder builder) {

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();

        JdkClientHttpRequestFactory requestFactory =
                new JdkClientHttpRequestFactory(httpClient);

        requestFactory.setReadTimeout(Duration.ofSeconds(5));

        this.restClient = builder
                .requestFactory(requestFactory)
                .build();
    }

    public ResponseEntity<String> executeGet(
            String targetKey,
            String endpoint,
            String username,
            String password
    ) {

        URI baseUrl = ALLOWED_TARGETS.get(targetKey);

        if (baseUrl == null) {
            throw new IllegalArgumentException(
                    "Applicazione di laboratorio non autorizzata"
            );
        }

        if (endpoint == null
                || !endpoint.matches("/api/[a-zA-Z0-9/_-]+")
                || endpoint.contains("//")) {

            throw new IllegalArgumentException(
                    "Endpoint non valido"
            );
        }

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {

            throw new IllegalArgumentException(
                    "Credenziali mancanti"
            );
        }

        URI requestUri = baseUrl.resolve(endpoint);

        return restClient.get()
                .uri(requestUri)
                .headers(headers ->
                        headers.setBasicAuth(username, password)
                )
                .exchange((request, response) -> {

                    String body = new String(
                            response.getBody().readAllBytes(),
                            StandardCharsets.UTF_8
                    );

                    return ResponseEntity
                            .status(response.getStatusCode())
                            .body(body);
                });
    }
}
