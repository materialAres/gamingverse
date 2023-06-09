package it.shine.gamingverse.dtos;

import it.shine.gamingverse.annotations.validations.YearConstraint;
import it.shine.gamingverse.entities.Console;
import it.shine.gamingverse.entities.ConsolePhoto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data @NoArgsConstructor @AllArgsConstructor
public class ConsoleDto {

    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 32, message = "Console name must be between 2 and 32 characters long")
    private String name;

    @Nullable
    private String developer;

    @YearConstraint
    private Year purchaseYear;

    @DecimalMin(value = "0.00", message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Nullable
    private List<Integer> photos;

    public ConsoleDto(Console console) {
        this.id = console.getId();
        this.name = console.getName();
        this.developer = console.getDeveloper();
        this.purchaseYear = console.getPurchaseYear();
        this.price = console.getPrice();
        this.photos = getPhotosIdFromConsole(console);
        this.createdAt = console.getCreatedAt();
        this.updatedAt = console.getUpdatedAt();
    }

    private static List<Integer> getPhotosIdFromConsole(Console console) {
        if (console.getPhotos() == null) {
            return null;
        } else {
            return console.getPhotos().stream()
                    .map(ConsolePhoto::getId)
                    .collect(Collectors.toList());
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsoleDto that = (ConsoleDto) o;
        return Objects.equals(
                getId(), that.getId())
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getDeveloper(), that.getDeveloper())
                && Objects.equals(getPurchaseYear(), that.getPurchaseYear())
                && Objects.equals(getPrice(), that.getPrice())
                && Objects.equals(getCreatedAt(), that.getCreatedAt())
                && Objects.equals(getUpdatedAt(), that.getUpdatedAt())
                && Objects.equals(getPhotos(), that.getPhotos()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDeveloper(),
                getPurchaseYear(), getPrice(), getCreatedAt(),
                getUpdatedAt(), getPhotos()
        );
    }
}
