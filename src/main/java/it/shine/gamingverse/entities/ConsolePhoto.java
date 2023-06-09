package it.shine.gamingverse.entities;

import it.shine.gamingverse.annotations.validations.MaxPhotoSizeConstraint;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "console_photos", schema = "public", catalog = "gamingverse")
public class ConsolePhoto extends AbstractPhoto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Nullable
    @Basic
    @Column(name = "filename")
    private String filename;

    @MaxPhotoSizeConstraint
    @Basic
    @Column(name = "content")
    private byte[] content;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "console_id")
    private Console console;

    @PrePersist
    private void setDefaultFilename() {
        if (filename == null || filename.isEmpty()) {
            filename = LocalDateTime.now() + ".jpg";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsolePhoto that = (ConsolePhoto) o;

        return Objects.equals(id, that.id) && Objects.equals(filename, that.filename) && Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filename);

        result = 31 * result + Arrays.hashCode(content);

        return result;
    }
}
