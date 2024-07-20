package dev.Innocent.service;

import dev.Innocent.DTO.MovieDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    MovieDTO addMovie(MovieDTO movieDTO, MultipartFile file) throws IOException;
    MovieDTO getMovie(Integer movieId);
    List<MovieDTO> getAllMovies();
    MovieDTO updateMovie(Integer movieId, MovieDTO movieDTO, MultipartFile file) throws IOException;
    String deleteMovie(Integer movieId);
}
