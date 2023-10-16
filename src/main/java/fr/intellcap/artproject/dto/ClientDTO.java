package fr.intellcap.artproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long clientId;
    private String email;
    private String password;
    private String cin;

    private String firstName;

    private String lastName;

    private Long age;


    private String address;


}
