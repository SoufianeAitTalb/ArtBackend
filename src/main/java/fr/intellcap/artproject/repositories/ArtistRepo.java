package fr.intellcap.artproject.repositories;

import fr.intellcap.artproject.entities.Artist;
import fr.intellcap.artproject.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepo extends JpaRepository<Artist,Long> {


    Optional<Artist> findById(Long id);
    Optional<Artist> findByEmail(String email);
}
