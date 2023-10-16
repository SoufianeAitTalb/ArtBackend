package fr.intellcap.artproject.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) // or other strategies like SINGLE_TABLE
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "password", length = 100)
    private String password;


}
