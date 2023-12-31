package fr.intellcap.artproject.dto;

import fr.intellcap.artproject.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long categoryId;
    private String name;
}
