package fr.intellcap.artproject.repositories;

import fr.intellcap.artproject.entities.Client;
import fr.intellcap.artproject.entities.Paint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintRepo extends JpaRepository<Paint,Long> {
}
