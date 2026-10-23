package dev.securelab.controller;

import dev.securelab.dto.CreateScanRequest;
import dev.securelab.dto.ScanResponse;
import dev.securelab.model.ScanExecution;
import dev.securelab.service.ScanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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

        // Conversione Entity -> DTO
        ScanResponse response = new ScanResponse(
                scan.getId(),
                scan.getTargetKey(),
                scan.getStatus(),
                scan.getCreatedAt(),
                scan.getStartedAt(),
                scan.getFinishedAt()
        );

        // Risposta HTTP 201 Created
        URI location = URI.create("/api/scans/" + scan.getId());

        return ResponseEntity
                .created(location)
                .body(response);

    }
}
