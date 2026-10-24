package dev.securelab.minidocs.service;

import dev.securelab.minidocs.model.Document;
import dev.securelab.minidocs.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService
{
    private final DocumentRepository documentRepository;

    public List<Document> getDocumentsByOwner(Long ownerId)
    {
        return documentRepository.findByOwner_Id(ownerId);
    }

    public Optional<Document> getDocumentForOwner(Long documentId, Long ownerId)
    {
        return documentRepository.findById(documentId)
                .filter( document -> document.getOwner().getId().equals(ownerId));
    }

}
