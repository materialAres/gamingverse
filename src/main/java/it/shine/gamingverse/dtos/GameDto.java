package it.shine.gamingverse.dtos;

import it.shine.gamingverse.entities.Game;
import it.shine.gamingverse.entities.GamePhoto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data @NoArgsConstructor
public class GameDto {

    private Integer id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 200, message = "Game title must be between 2 and 200 characters long")
    private String title;

    @Nullable
    private String genre;

    @DecimalMin(value = "0.00", message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    @Nullable
    private String developer;

    @Nullable
    private String publisher;

    @NotBlank(message = "Console is required")
    @Size(min = 2, max = 32, message = "Console name must be between 2 and 32 characters long")
    private String console;

    @Nullable
    // TODO display only date
    private LocalDateTime released;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Nullable
    private List<Integer> photos;

    public GameDto(Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.genre = game.getGenre();
        this.price = game.getPrice();
        this.developer = game.getDeveloper();
        this.publisher = game.getPublisher();
        this.console = game.getConsole();
        this.released = game.getReleased();
        this.createdAt = game.getCreatedAt();
        this.updatedAt = game.getUpdatedAt();
        this.photos = getPhotosIdFromGame(game);
    }

    private static List<Integer> getPhotosIdFromGame(Game game) {
        if (game.getPhotos() == null) {
            return null;
        } else {
            return game
                    .getPhotos()
                    .stream()
                    .map(GamePhoto::getId)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof GameDto gameDto)) return false;

        return Objects.equals(getId(), gameDto.getId()) &&
                Objects.equals(getTitle(), gameDto.getTitle()) &&
                Objects.equals(getGenre(), gameDto.getGenre()) &&
                Objects.equals(getPrice(), gameDto.getPrice()) &&
                Objects.equals(getDeveloper(), gameDto.getDeveloper()) &&
                Objects.equals(getPublisher(), gameDto.getPublisher()) &&
                Objects.equals(getConsole(), gameDto.getConsole()) &&
                Objects.equals(getReleased(), gameDto.getReleased()) &&
                Objects.equals(getCreatedAt(), gameDto.getCreatedAt()) &&
                Objects.equals(getUpdatedAt(), gameDto.getUpdatedAt()) &&
                Objects.equals(getPhotos(), gameDto.getPhotos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getGenre(),
                getPrice(), getDeveloper(), getPublisher(),
                getConsole(), getReleased(), getCreatedAt(),
                getUpdatedAt(), getPhotos());
    }

}