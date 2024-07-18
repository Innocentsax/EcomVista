package dev.Innocent.service.Impl;

import dev.Innocent.DTO.MovieDTO;
import dev.Innocent.entities.Movie;
import dev.Innocent.repository.MovieRepository;
import dev.Innocent.service.FileService;
import dev.Innocent.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final FileService fileService;

    @Value("${project.poster}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    @Override
    public MovieDTO addMovie(MovieDTO movieDTO, MultipartFile file) throws IOException {
        /*
        1. Upload the file to the server
        2. Set the value of the file "Poster" to file name
        3. Map DTO to movie object
        4. Save the movie object to the database
        5. Generate the poster URL
        6. Map movie object to DTO object and return it
         */
        String fileName = fileService.uploadFile(path, file);
        movieDTO.setPoster(fileName);

        Movie movie = new Movie(
                movieDTO.getMovieId(),
                movieDTO.getTitle(),
                movieDTO.getDirector(),
                movieDTO.getStudio(),
                movieDTO.getMovieCast(),
                movieDTO.getReleaseYear(),
                movieDTO.getPoster(),
                movieDTO.getPosterUrl()
        );

        Movie savedMovie = movieRepository.save(movie);
        String posterUrl = baseUrl + "/file/" + fileName;

        return new MovieDTO(
                savedMovie.getMovieId(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getReleaseYear(),
                savedMovie.getPoster(),
                posterUrl
        );
    }

    @Override
    public MovieDTO getMovie(Integer movieId) {
        return null;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return List.of();
    }
}
