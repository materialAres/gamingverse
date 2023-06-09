package it.shine.gamingverse.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("game")
@Table(name = "games", schema = "public", catalog = "gamingverse")
// @EntityListeners(ProductEntityListener.class)
public class Game extends Product {

    @Basic
    @Column(name = "title")
    @NotNull
    private String title;

    @Basic
    @Column(name = "genre")
    private String genre;

    @Basic
    @Column(name = "console")
    @NotNull
    private String console;

    @Basic
    @Column(name = "released")
    private LocalDateTime released;

    @OneToMany(mappedBy = "game")
    @JsonBackReference
    private List<GamePhoto> photos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game that = (Game) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(title, that.title)
                && Objects.equals(genre, that.genre)
                && Objects.equals(getPrice(), that.getPrice())
                && Objects.equals(getDeveloper(), that.getDeveloper())
                && Objects.equals(getPublisher(), that.getPublisher())
                && Objects.equals(console, that.console)
                && Objects.equals(released, that.released)
                && Objects.equals(getCreatedAt(), that.getCreatedAt())
                && Objects.equals(getUpdatedAt(), that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(), title, genre,
                getPrice(), getDeveloper(),
                getPublisher(), console,
                released, getCreatedAt(),
                getUpdatedAt());
    }

}
