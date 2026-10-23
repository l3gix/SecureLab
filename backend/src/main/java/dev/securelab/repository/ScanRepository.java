package dev.securelab.repository;

import dev.securelab.model.ScanExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// creazione automatica dei metodi con Spring Data JPA

@Repository // anche se lo riconosce in automatico
public interface ScanRepository extends JpaRepository<ScanExecution, Long> // <T,ID> T tipo da gestire , ID tipo della sua chiave primaria
{

}
