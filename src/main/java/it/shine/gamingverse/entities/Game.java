package it.shine.gamingverse.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("game")
@Table(name = "games", schema = "public", catalog = "gamingverse")
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

    @OneToMany(mappedBy = "game")
    @JsonBackReference
    private List<GamePhoto> photos;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Basic
    @Column(name = "developer")
    private String developer;

    @Basic
    @Column(name = "publisher")
    private String publisher;

    @Basic
    @Column(name = "released")
    private Year released;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // TODO the seller will be the authenticated user
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer seller;


    @PrePersist
    protected void onCreate() {
        setCreatedAt(LocalDateTime.now());
        setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() {
        setUpdatedAt(LocalDateTime.now());
    }

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
                && Objects.equals(getReleased(), that.getReleased())
                && Objects.equals(console, that.console)
                && Objects.equals(getCreatedAt(), that.getCreatedAt())
                && Objects.equals(getUpdatedAt(), that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(), title, genre,
                getPrice(), getDeveloper(),
                getPublisher(), getReleased(),
                console, getCreatedAt(), getUpdatedAt());
    }

}
