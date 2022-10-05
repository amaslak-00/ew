package animeweb.demo.anime.model;

import animeweb.demo.studio.Studio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;
    private String genre;
    private Integer numberOfSeasons;
    private LocalDate dateOfPremiere;
    private Boolean isOngoing;
    private String description;

}
