package fr.intellcap.artproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paint_id")
    private Long paintId;



    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "materials", length = 100)
    private String materials;

    @Column(name = "x_dimension")
    @JsonProperty("xDimension")
    private double xDimension;
    @Column(name = "y_dimension")
    @JsonProperty("yDimension")
    private double yDimension;
    @Column(name = "desc_paint", length = 100)
    private String descPaint;

    @Column(name = "desc_artist", length = 100)
    private String descArtist;

    private String image;

    private double price;
    private long quantity;
    private String inventoryStatus;
    private long rating;


    @ManyToOne
    @JsonIgnore
    @JsonBackReference
//    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private Artist artist;

    @OneToMany(mappedBy = "paint")
    @JsonIgnore
    private List<Command> commands;

    @JsonIgnore
    @ManyToOne
    private Category category ;


}
