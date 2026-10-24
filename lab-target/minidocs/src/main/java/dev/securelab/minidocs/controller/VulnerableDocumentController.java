package dev.securelab.minidocs.controller;

import dev.securelab.minidocs.dto.DocumentResponse;
import dev.securelab.minidocs.service.DocumentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/lab/vulnerable/documents")
@RequiredArgsConstructor
@Slf4j // crea logger
public class VulnerableDocumentController {

    private final DocumentService documentService;

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentResponse> getDocument(
            @PathVariable Long documentId,
            Principal principal // data da spring sercurity
    ) {

        log.info(
                "Utente {} richiede il documento {}",
                principal.getName(),
                documentId
        );

        return ResponseEntity.of(
                documentService.getDocumentWithoutAuthorization(documentId)
                        .map(document -> new DocumentResponse(
                                document.getId(),
                                document.getTitle(),
                                document.getContent()
                        ))
        );
    }
}