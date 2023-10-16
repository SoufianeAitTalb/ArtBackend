package fr.intellcap.artproject.repositories;

import fr.intellcap.artproject.entities.Artist;
import fr.intellcap.artproject.entities.Client;
import fr.intellcap.artproject.entities.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandRepo extends JpaRepository<Command,Long> {
    Optional<Command> findById(Long id);

}
