package dev.Innocent.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.Innocent.DTO.MovieDTO;
import dev.Innocent.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/movie")
@Slf4j
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<MovieDTO> addMovieHandler(@RequestPart MultipartFile file, @RequestPart String movieDTO) throws IOException {
        MovieDTO movie = convertToMovieDTO(movieDTO);
        MovieDTO savedMovie = movieService.addMovie(movie, file);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    private MovieDTO convertToMovieDTO(String movieDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(movieDTO, MovieDTO.class);
    }

}
