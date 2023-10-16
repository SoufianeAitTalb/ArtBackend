package fr.intellcap.artproject.repositories;

import fr.intellcap.artproject.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {


    Optional<Client> findById(Long id);
    Optional<Client> findByEmail(String email);
}
