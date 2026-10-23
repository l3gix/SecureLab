package dev.securelab.controller;

import dev.securelab.dto.CreateScanRequest;
import dev.securelab.dto.ScanResponse;
import dev.securelab.model.ScanExecution;
import dev.securelab.service.ScanService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/scans")
@RequiredArgsConstructor
public class ScanController
{
    private final ScanService scanService;

    @PostMapping
    public ResponseEntity<ScanResponse> createScan(
            @Valid @RequestBody CreateScanRequest request
            ) {

        // Chiamata al Service
        ScanExecution scan = scanService.createScan(
                request.targetKey()
        );

        ScanResponse response = toResponse(scan);

        // Risposta HTTP 201 Created
        URI location = URI.create("/api/scans/" + scan.getId());

        return ResponseEntity
                .created(location)
                .body(response);

    }

    @GetMapping
    public List<ScanResponse> getAllScans() {
        return scanService.getAllScans()
                .stream()
                .map(scan -> toResponse(scan)) // funzione lanba stream
                .toList();
    }

    // GET /api/scans/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ScanResponse> getScanById(
            @PathVariable Long id) {

        return ResponseEntity.of(
                scanService.getScanById(id)
                        .map(scan -> toResponse(scan))
        );
    }

    // Converte un'entità JPA in un DTO
    private ScanResponse toResponse(ScanExecution scan) {

        return new ScanResponse(
                scan.getId(),
                scan.getTargetKey(),
                scan.getStatus(),
                scan.getCreatedAt(),
                scan.getStartedAt(),
                scan.getFinishedAt()
        );
    }
}
