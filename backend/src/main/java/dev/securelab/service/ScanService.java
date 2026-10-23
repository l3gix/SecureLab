package dev.securelab.service;

import dev.securelab.model.ScanExecution;
import dev.securelab.repository.ScanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

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

    // Recupera tutte le scansioni
    public List<ScanExecution> getAllScans() {
        return scanRepository.findAll();
    }

    // Recupera una scansione tramite Id
    // Optional puo essere vuoto ecco perchè lo scelto
    public Optional<ScanExecution>  getScanById(Long id) {
        return scanRepository.findById(id);
    }
}
