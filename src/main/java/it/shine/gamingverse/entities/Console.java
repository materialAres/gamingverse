package it.shine.gamingverse.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.shine.gamingverse.entities.entitylisteners.ProductEntityListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "consoles", schema = "public", catalog = "gamingverse")
@DiscriminatorValue("console")
// @EntityListeners(ProductEntityListener.class)
public class Console {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    @NotNull
    private String name;

    @Basic
    @Column(name = "developer")
    private String developer;

    @Basic
    @Column(name = "purchase_year")
    private Year purchaseYear;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "id")
    @JsonBackReference
    private List<ConsolePhoto> photos;

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

        Console console = (Console) o;

        if (!Objects.equals(getId(), console.getId())) return false;
        if (!Objects.equals(name, console.name)) return false;
        if (!Objects.equals(developer, console.developer)) return false;
        if (!Objects.equals(purchaseYear, console.purchaseYear)) return false;
        if (!Objects.equals(price, console.price)) return false;
        if (!Objects.equals(createdAt, console.createdAt)) return false;
        if (!Objects.equals(updatedAt, console.updatedAt)) return false;
        if (!Objects.equals(photos, console.photos)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (developer != null ? developer.hashCode() : 0);
        result = 31 * result + (purchaseYear != null ? purchaseYear.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        return result;
    }

}
