package dev.securelab.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;

public class ScannerHttpClientIntegrationTest {

    @Test
    @EnabledIfEnvironmentVariable(
            named = "SECURELAB_TEST_PASSWORD",
            matches = ".+"
    )
    void shouldExecuteAuthenticatedGetRequest() {

        // 1. Recuperiamo i parametri dalle variabili d'ambiente
        String target = System.getenv("SECURELAB_TEST_TARGET");
        String endpoint = System.getenv("SECURELAB_TEST_ENDPOINT");
        String username = System.getenv("SECURELAB_TEST_USERNAME");
        String password = System.getenv("SECURELAB_TEST_PASSWORD");

        // 2. Creiamo il nostro client HTTP
        ScannerHttpClient client =
                new ScannerHttpClient(RestClient.builder());

        // 3. Eseguiamo la richiesta HTTP
        ResponseEntity<String> response = client.executeGet(
                target,
                endpoint,
                username,
                password
        );

        // 4. Verifichiamo lo stato HTTP
        assertEquals(200, response.getStatusCode().value());

        // 5. Verifichiamo che la risposta contenga dei dati
        assertNotNull(response.getBody());

        // 6. Verifichiamo che il JSON contenga il campo title
        assertTrue(response.getBody().contains("\"title\""));

        // 7. Visualizziamo il risultato
        System.out.println("HTTP Status: "
                + response.getStatusCode().value());

        System.out.println("Response Body: "
                + response.getBody());
    }
}