package com.example.cinemavericks.services;

import com.example.cinemavericks.models.Movie;
import com.example.cinemavericks.models.MovieList;
import com.example.cinemavericks.models.MovieListDTO;
import com.example.cinemavericks.models.User;
import com.example.cinemavericks.repositories.MovieListRepository;
import com.example.cinemavericks.repositories.MovieRepository;
import com.example.cinemavericks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieListService {
    @Autowired
    MovieListRepository movieListRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    public List<MovieList> getAllMovieList() {
        return movieListRepository.findAll();
    }

    public Optional<MovieList> getMovieListById(Long id) {
        return movieListRepository.findById(id);
    }

    public MovieList addMovieList(MovieListDTO movieListDTO) {
        Optional<User> user = userRepository.findById(movieListDTO.getUserId());
        if (user.isPresent()) {
            return movieListRepository.save(new MovieList(user.get(), movieListDTO.getTitle()));
        } else {
            //UserId not valid
            return null;
        }

    }

    public MovieList addMovieInMovieList(Long id, MovieListDTO movieListDTO) {
        //find MovieList
        Optional<MovieList> movieList = movieListRepository.findById(id);
        List<Movie> moviesToAdd = new ArrayList<>();

        if(movieList.isEmpty()){
            return null;
        }

        for (Long movieId : movieListDTO.getMovieIds()) {
            Optional<Movie> movie = movieRepository.findById(movieId);
            if(movie.isPresent()){
                moviesToAdd.add(movie.get());
            }
        }

        for (Movie movie : moviesToAdd){
            //CHECK if specified movie is not on the movieList
            if(!movieList.get().getMovies().contains(movie)){
                movieList.get().addMovie(movie);
            }
        }

        //save movieList
        return movieListRepository.save(movieList.get());

    }

    public MovieList removeMovieInMovieList(Long id, MovieListDTO movieListDTO) {
        MovieList movieList = movieListRepository.findById(id).get();
        List<Movie> removeList = new ArrayList<>();

        //Add the movie to be deleted IN removeList
        for (Long movieId : movieListDTO.getMovieIds()){
            Optional<Movie> movie = movieRepository.findById(movieId);
            if(movie.isPresent()){
                removeList.add(movie.get());
            }
        }

        //REMOVE movie from movieList using removeList
        for (Movie movie : removeList){
            //CHECK if specified movie is on the movieList
            if(movieList.getMovies().contains(movie)){
                movieList.removeMovie(movie);
            }
        }
        return movieListRepository.save(movieList);
    }

    public void removeMovieList(Long id) {
        movieListRepository.deleteById(id);
    };

    //EXTENSION:
    // Setting Movie List privacy settings
    public void setMovieListVisibility(Long movieListId, Boolean isPublic){
        MovieList targetMovieList = movieListRepository.findById(movieListId).get();
        targetMovieList.setPublic(isPublic);
        movieListRepository.save(targetMovieList);

    }

}
