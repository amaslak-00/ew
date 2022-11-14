package animeweb.demo.useranime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAnimeDTO {

    private long id;
    private Boolean isWatched;
    private long userId;
    private long animeId;
    private String comment;


}
