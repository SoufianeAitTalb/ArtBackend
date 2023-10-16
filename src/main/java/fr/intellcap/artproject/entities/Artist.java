package fr.intellcap.artproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DiscriminatorValue("ARTIST")
public class Artist extends User implements Serializable {


    @Column(name = "cin", length = 100)
    private String cin;
    @Column(name = "first_name", length = 100)
    private String firstName;
    @Column(name = "last_name", length = 100)
    private String lastName;
    @Column(name = "age", length = 100)
    private Long age;
    @Column(name = "painting_style", length = 100)
    private String paintingStyle;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<Paint> paints;

}
