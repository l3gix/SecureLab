package dev.securelab.minidocs.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lab_documents")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Document
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Grazie a LAZY, il caricamento completo dell'oggetto User può essere rimandato a quando ne avremo realmente bisogno.
    @JoinColumn(name = "owner_id",nullable = false)
    private User owner;

    public Document(String title, String content, User owner) {
        this.title = title;
        this.content = content;
        this.owner = owner;
    }
}
