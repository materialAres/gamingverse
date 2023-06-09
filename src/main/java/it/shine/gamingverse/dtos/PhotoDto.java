package it.shine.gamingverse.dtos;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public abstract class PhotoDto {

    private Integer id;
    @Nullable
    private String filename;
    private String content;

}
