package dev.Innocent.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Integer movieId;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Director is required")
    private String director;

    @NotBlank(message = "Studio is required")
    private String studio;
    private Set<String> movieCast;
    private Integer releaseYear;
    @NotBlank(message = "Poster is required")
    private String poster;
    @NotBlank(message = "PosterUrl is required")
    private String posterUrl;

}
