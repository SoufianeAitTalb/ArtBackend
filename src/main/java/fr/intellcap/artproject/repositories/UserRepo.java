package fr.intellcap.artproject.repositories;

import fr.intellcap.artproject.entities.Client;
import fr.intellcap.artproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
