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
@Table(name = "consoles", schema = "public", catalog = "gamingverse")
@DiscriminatorValue("console")
// @EntityListeners(ProductEntityListener.class)
public class Console extends Product {

    @Basic
    @Column(name = "name")
    @NotNull
    private String name;

    @Basic
    @Column(name = "purchase_year")
    private Year purchaseYear;

    @OneToMany(mappedBy = "id")
    @JsonBackReference
    private List<ConsolePhoto> photos;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer seller;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Console console = (Console) o;

        if (!Objects.equals(getId(), console.getId())) return false;
        if (!Objects.equals(name, console.name)) return false;
        if (!Objects.equals(getDeveloper(), console.getDeveloper())) return false;
        if (!Objects.equals(purchaseYear, console.purchaseYear)) return false;
        if (!Objects.equals(getPrice(), console.getPrice())) return false;
        if (!Objects.equals(getCreatedAt(), console.getCreatedAt())) return false;
        if (!Objects.equals(getUpdatedAt(), console.getUpdatedAt())) return false;
        if (!Objects.equals(photos, console.photos)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (getDeveloper() != null ? getDeveloper().hashCode() : 0);
        result = 31 * result + (purchaseYear != null ? purchaseYear.hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        result = 31 * result + (getUpdatedAt() != null ? getUpdatedAt().hashCode() : 0);
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        return result;
    }

}
