package fr.intellcap.artproject.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.intellcap.artproject.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaintDTO {
    private Long paintId;

    private String name;


    private String materials;

    @JsonProperty("xDimension")
    private double xDimension;

    @JsonProperty("yDimension")
    private double yDimension;

    private String descPaint;


    private String descArtist;
    private Long artistId;
    private String image;
    private double price;
    private long quantity;
    private String inventoryStatus;
    private long rating;


    private Category category;


}
