package dev.securelab.minidocs.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lab_users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    public User(String username){
        this.username = username;
    }
}
