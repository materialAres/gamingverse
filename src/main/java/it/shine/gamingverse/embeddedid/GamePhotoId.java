package it.shine.gamingverse.embeddedid;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor
public class GamePhotoId implements Serializable {

    private Long game;
    private Long photo;

}
