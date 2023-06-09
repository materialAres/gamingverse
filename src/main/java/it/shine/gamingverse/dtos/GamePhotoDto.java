package it.shine.gamingverse.dtos;

import it.shine.gamingverse.entities.ConsolePhoto;
import it.shine.gamingverse.entities.GamePhoto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data @NoArgsConstructor @AllArgsConstructor
public class GamePhotoDto extends PhotoDto {

    @NotNull
    private Integer game;

    public GamePhotoDto(GamePhoto photo) {
        this.setId(photo.getId());
        this.setFilename(photo.getFilename());
        this.setContent(Base64.getEncoder().encodeToString(photo.getContent()));
        this.setGame(photo.getGame().getId());
    }

}
