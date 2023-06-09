package it.shine.gamingverse.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.shine.gamingverse.entities.entitylisteners.ProductEntityListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("game")
@Table(name = "games", schema = "public", catalog = "gamingverse")
// @EntityListeners(ProductEntityListener.class)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "title")
    @NotNull
    private String title;

    @Basic
    @Column(name = "genre")
    private String genre;

    @Basic
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
    @Column(name = "console")
    @NotNull
    private String console;

    @Basic
    @Column(name = "released")
    private Date released;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "game")
    @JsonBackReference
    private List<GamePhoto> photos;

    // TODO the seller will be the authenticated user
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer seller;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game that = (Game) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(title, that.title)
                && Objects.equals(genre, that.genre)
                && Objects.equals(price, that.price)
                && Objects.equals(developer, that.developer)
                && Objects.equals(publisher, that.publisher)
                && Objects.equals(console, that.console)
                && Objects.equals(released, that.released)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(), title, genre,
                price, developer,
                publisher, console,
                released, createdAt,
                updatedAt);
    }

}
