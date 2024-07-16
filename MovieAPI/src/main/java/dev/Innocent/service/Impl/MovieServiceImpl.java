package dev.Innocent.service.Impl;

import dev.Innocent.DTO.MovieDTO;
import dev.Innocent.repository.MovieRepository;
import dev.Innocent.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public MovieDTO addMovie(MovieDTO movieDTO, MultipartFile file) {
        /*
        1. Upload the file to the server
        2. Set the value of the file "Poster" to file name
        3. Map DTO to movie object
         */
        return null;
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
