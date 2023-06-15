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
import java.util.List;
import java.util.Objects;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "consoles", schema = "public", catalog = "gamingverse")
@DiscriminatorValue("console")
public class Console extends Product {

    @Basic
    @Column(name = "console_name")
    @NotNull
    private String consoleName;

    @Basic
    @Column(name = "developer")
    private String developer;

    @Basic
    @Column(name = "released")
    private Year released;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "id")
    @JsonBackReference
    private List<ConsolePhoto> photos;

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

        Console console = (Console) o;

        if (!Objects.equals(getId(), console.getId())) return false;
        if (!Objects.equals(getConsoleName(), console.getConsoleName())) return false;
        if (!Objects.equals(getDeveloper(), console.getDeveloper())) return false;
        if (!Objects.equals(getReleased(), console.getReleased())) return false;
        if (!Objects.equals(getPrice(), console.getPrice())) return false;
        if (!Objects.equals(getCreatedAt(), console.getCreatedAt())) return false;
        if (!Objects.equals(getUpdatedAt(), console.getUpdatedAt())) return false;
        if (!Objects.equals(getPhotos(), console.getPhotos())) return false;
        if (!Objects.equals(getSeller(), console.getSeller())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;

        result = 31 * result + (getConsoleName() != null ? getConsoleName().hashCode() : 0);
        result = 31 * result + (getDeveloper() != null ? getDeveloper().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        result = 31 * result + (getUpdatedAt() != null ? getUpdatedAt().hashCode() : 0);
        result = 31 * result + (getReleased() != null ? getReleased().hashCode() : 0);
        result = 31 * result + (getPhotos() != null ? getPhotos().hashCode() : 0);
        result = 31 * result + (getSeller() != null ? getSeller().hashCode() : 0);

        return result;
    }

}
