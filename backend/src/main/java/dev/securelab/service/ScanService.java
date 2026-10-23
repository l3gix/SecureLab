package dev.securelab.service;

import dev.securelab.model.ScanExecution;
import dev.securelab.repository.ScanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScanService
{
    // esendo che ci sta un solo costruttore capisce in automatico e lombok genera il costruttore
    private final ScanRepository scanRepository;

    public ScanExecution createScan(String targetKey) {

        if (targetKey == null || targetKey.isBlank()) {
            throw new IllegalArgumentException("Il target non può essere vuoto");
        }

        ScanExecution scan = new ScanExecution(targetKey.trim());

        return scanRepository.save(scan);
    }
}
