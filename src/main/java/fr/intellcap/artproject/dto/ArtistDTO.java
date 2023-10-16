package fr.intellcap.artproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO {
    private Long artistId;
    private String email;
    private String password;

    private String cin;

    private String firstName;

    private String lastName;

    private Long age;


    private String paintingStyle;


}
