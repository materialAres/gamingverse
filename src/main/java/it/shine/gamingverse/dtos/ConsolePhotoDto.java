package it.shine.gamingverse.dtos;

import it.shine.gamingverse.entities.ConsolePhoto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data @NoArgsConstructor @AllArgsConstructor
public class ConsolePhotoDto extends PhotoDto {

    @NotNull
    private Integer consoleId;

    public ConsolePhotoDto(ConsolePhoto photo) {
        this.setId(photo.getId());
        this.setFilename(photo.getFilename());
        this.setContent(Base64.getEncoder().encodeToString(photo.getContent()));
        this.setConsoleId(photo.getConsole().getId());
    }

}
