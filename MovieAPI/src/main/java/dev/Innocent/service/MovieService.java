package dev.Innocent.service;

import dev.Innocent.DTO.MovieDTO;
import dev.Innocent.DTO.MoviePageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    MovieDTO addMovie(MovieDTO movieDTO, MultipartFile file) throws IOException;
    MovieDTO getMovie(Integer movieId);
    List<MovieDTO> getAllMovies();
    MovieDTO updateMovie(Integer movieId, MovieDTO movieDTO, MultipartFile file) throws IOException;
    String deleteMovie(Integer movieId) throws IOException;
    MoviePageResponse getAllMoviesWithPagination(Integer pageNumber, Integer pageSize);
    MoviePageResponse getAllMoviesWithPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String order);
}
