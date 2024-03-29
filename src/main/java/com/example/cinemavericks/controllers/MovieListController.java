package com.example.cinemavericks.controllers;

import com.example.cinemavericks.models.MovieList;
import com.example.cinemavericks.models.MovieListDTO;
import com.example.cinemavericks.services.MovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movielists")
public class MovieListController {
    @Autowired
    MovieListService movieListService;

    // EXTENSION!
    //Handles following:
    // GET movieLists - only public ones now though!
    @GetMapping
    public ResponseEntity<List<MovieList>> gePublicMovieList() {
        List<MovieList> allMovieLists = movieListService.getAllMovieList();
        List<MovieList> publicMovieList = new ArrayList<>();
        for (MovieList movieList : allMovieLists) {
            if (movieList.isPublic()) {
                publicMovieList.add(movieList);
            }
        }
        return new ResponseEntity<>(publicMovieList,HttpStatus.OK);
    }




    //Display specific movieList by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieList> getMovieListById(@PathVariable Long id) {
        Optional<MovieList> movieList = movieListService.getMovieListById(id);
        if (movieList.isPresent()) {
            return new ResponseEntity<>(movieList.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //    Creating movieList
    @PostMapping
    public ResponseEntity<MovieList> addMovieList(@RequestBody MovieListDTO movieListDTO) {
        MovieList movieList = movieListService.addMovieList(movieListDTO);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    //    Updating specific fields of the movieList (eg: adding or removing the movies)
    @PatchMapping(value = "/{id}/addMovies")
    public ResponseEntity<MovieList> updatingMovieList(@PathVariable Long id, @RequestBody MovieListDTO movieListDTO) {
        MovieList movieList = movieListService.addMovieInMovieList(id, movieListDTO);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/removeMovies")
    public ResponseEntity<MovieList> removeMovieInMovieList(@PathVariable Long id, @RequestBody MovieListDTO movieListDTO) {
        Optional<MovieList> targetMovieList = movieListService.getMovieListById(id);
        if (targetMovieList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        MovieList movieList = movieListService.removeMovieInMovieList(id, movieListDTO);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    // EXT: Setting a movieList to public or private
    @PatchMapping("/{movieListId}/setPublic{setting}")
    public ResponseEntity<MovieList> setMovieListVisibility(@PathVariable Long movieListId, @PathVariable Boolean setting) {

        Optional<MovieList> targetMovieList = movieListService.getMovieListById(movieListId);
        if(targetMovieList.isPresent()) {
            movieListService.setMovieListVisibility(movieListId, setting);
            return new ResponseEntity<>(targetMovieList.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    //    Deleting a movieList
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> removeMovieList(@PathVariable Long id) {
        Optional<MovieList> targetMovieList = movieListService.getMovieListById(id);
        if (targetMovieList.isEmpty()) {
            return new ResponseEntity<>("MovieList does not exist", HttpStatus.NOT_FOUND);
        }
        movieListService.removeMovieList(id);
        return new ResponseEntity<>("MovieList Deleted", HttpStatus.OK);
    }

}





