package dev.securelab.model;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "scan_executions")

//Lombok
@Getter //getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)// costruttore protected
public class ScanExecution
{

    @Id // chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera la chiave primaria
    private Long id;

    @Column(name = "target_key", nullable = false, length = 50)
    private String targetKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ScanStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "started_at")
    private Instant startedAt;

    @Column(name = "finished_at")
    private Instant finishedAt;

    public ScanExecution(String targetKey) {
        this.targetKey = targetKey;
        this.status = ScanStatus.CREATED;
        this.createdAt = Instant.now();
    }
}
