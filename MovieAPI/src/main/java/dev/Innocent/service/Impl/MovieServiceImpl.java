package dev.Innocent.service.Impl;

import dev.Innocent.DTO.MovieDTO;
import dev.Innocent.DTO.MoviePageResponse;
import dev.Innocent.entities.Movie;
import dev.Innocent.exception.FileExistException;
import dev.Innocent.exception.MovieNotFoundException;
import dev.Innocent.repository.MovieRepository;
import dev.Innocent.service.FileService;
import dev.Innocent.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        if(Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new FileExistException("File already exists! Please upload a new file");
        }

        String fileName = fileService.uploadFile(path, file);
        movieDTO.setPoster(fileName);

        Movie movie = new Movie(
                null,
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
        // Check the data in DB if exist, fetch the data of given Id
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found with Id = " + movieId));

        // Generate the poster URL
        String posterUrl = baseUrl + "/file/" + movie.getPoster();

        // Map movie object to DTO object and return it
        return new MovieDTO(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        // Fetch all the data from the database
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOS = new ArrayList<>();

        // Iterate over the list of movies and generate the poster URL
        for (Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            movieDTOS.add(new MovieDTO(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    posterUrl
            ));
        }
        return movieDTOS;
    }

    @Override
    public MovieDTO updateMovie(Integer movieId, MovieDTO movieDTO, MultipartFile file) throws IOException {
        /*
        1. Check if the movie exists in the database with the given movieId
        2. Check if the file exists in the server | If it exists, delete the file associated with the record
         Upload the file to the server
        3. Set the value of the file "Poster" to file name
        4. Map DTO to movie object
         */
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found with Id = " + movieId));

        String fileName = movie.getPoster();
        if(file != null){
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            fileName = fileService.uploadFile(path, file);
        }
        movieDTO.setPoster(fileName);

        Movie updatedMovie = new Movie(
                movie.getMovieId(),
                movieDTO.getTitle(),
                movieDTO.getDirector(),
                movieDTO.getStudio(),
                movieDTO.getMovieCast(),
                movieDTO.getReleaseYear(),
                movieDTO.getPoster(),
                movieDTO.getPosterUrl()
        );
        Movie savedMovie = movieRepository.save(updatedMovie);
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
    public String deleteMovie(Integer movieId) throws IOException {
        /*
        1. Check if the movie exists in the database with the given movieId
        2. Delete the file associated with the record
        3. Delete the record from the database
         */
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found with Id = " + movieId));
        Integer id = movie.getMovieId();
        Files.deleteIfExists(Paths.get(path + File.separator + movie.getPoster()));
        movieRepository.delete(movie);
        return "Movie deleted successfully with Id = " + id;
    }

    @Override
    public MoviePageResponse getAllMoviesWithPagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        List<Movie> movies = moviePage.getContent();

        List<MovieDTO> movieDTOS = new ArrayList<>();
        for (Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            movieDTOS.add(new MovieDTO(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    posterUrl
            ));
        }
        return new MoviePageResponse(
                movieDTOS,
                moviePage.getNumber(),
                moviePage.getSize(),
                moviePage.getTotalPages(),
                moviePage.getNumber(),
                moviePage.isLast(),
                moviePage.isFirst(),
                moviePage.getTotalElements()
        );
    }

    @Override
    public MoviePageResponse getAllMoviesWithPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        List<Movie> movies = moviePage.getContent();

        List<MovieDTO> movieDTOS = new ArrayList<>();
        for (Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            movieDTOS.add(new MovieDTO(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    posterUrl
            ));
        }
        return new MoviePageResponse(
                movieDTOS,
                moviePage.getNumber(),
                moviePage.getSize(),
                moviePage.getTotalPages(),
                moviePage.getNumber(),
                moviePage.isLast(),
                moviePage.isFirst(),
                moviePage.getTotalElements()
        );
    }
}
