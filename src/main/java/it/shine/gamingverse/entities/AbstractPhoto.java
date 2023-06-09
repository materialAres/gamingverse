package it.shine.gamingverse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public abstract class AbstractPhoto {

    private Integer id;

    private String filename;

    private byte[] content;

}
