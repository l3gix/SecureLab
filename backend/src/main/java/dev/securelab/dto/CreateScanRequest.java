package dev.securelab.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// record funzione di java per trasportare dati
public record CreateScanRequest(
        @NotBlank(message = "Il target è obbligatorio") // campo non nullo
        @Size(max = 50, message = "Il target non può superare 50 caratteri") // stringa superiore a 50 caratteri
        String targetKey
) {
}
