package dev.securelab.minidocs.controller;

import dev.securelab.minidocs.dto.DocumentResponse;
import dev.securelab.minidocs.model.Document;
import dev.securelab.minidocs.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lab/documents")
@RequiredArgsConstructor
public class DocumentController
{
    private final DocumentService documentService;

    @GetMapping
    public List<DocumentResponse> getDocumentsByOwner(
            @RequestParam("ownerId") long ownerId
    )
    {
        return documentService.getDocumentsByOwner(ownerId)
                .stream()
                .map(document -> toResponse(document))
                .toList();
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentResponse> getDocumentById(
            @PathVariable("documentId") Long documentId,
            @RequestParam("ownerId") Long ownerId
    ) {

        return ResponseEntity.of(
                documentService.getDocumentForOwner(documentId, ownerId)
                        .map(document -> toResponse(document))
        );
    }

    private DocumentResponse toResponse(Document document) {

        return new DocumentResponse(
                document.getId(),
                document.getTitle(),
                document.getContent()
        );
    }
}
