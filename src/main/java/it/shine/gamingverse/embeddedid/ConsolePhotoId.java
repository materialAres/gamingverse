package it.shine.gamingverse.embeddedid;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor
public class ConsolePhotoId implements Serializable {

    private Long console;
    private Long photo;

}
