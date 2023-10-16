package fr.intellcap.artproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.Serializable;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Command implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "command_id")
    private Long commandId;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
//    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private Client client;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
//    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private Paint paint;
}
