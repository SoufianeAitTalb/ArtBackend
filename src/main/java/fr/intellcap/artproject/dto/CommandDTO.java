package fr.intellcap.artproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandDTO {
    private Long commadId;
    private Long idClient;
    private Long idPaint;
}
