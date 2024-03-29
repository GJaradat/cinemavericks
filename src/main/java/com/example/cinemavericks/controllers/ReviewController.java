package com.example.cinemavericks.controllers;

import com.example.cinemavericks.models.PostReviewDTO;
import com.example.cinemavericks.models.Review;
import com.example.cinemavericks.models.ReviewDTO;
import com.example.cinemavericks.repositories.ReviewRepository;
import com.example.cinemavericks.services.MovieService;
import com.example.cinemavericks.services.ReviewService;
import com.example.cinemavericks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody PostReviewDTO postReviewDTO){
        if (postReviewDTO.getRating() < 0 || postReviewDTO.getRating() > 5) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if(movieService.getMovieById(postReviewDTO.getMovieId()).isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if(userService.getUserById(postReviewDTO.getUserId()).isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Review newReview = reviewService.createReview(postReviewDTO);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }
    
    @PatchMapping("/{reviewId}")
    public ResponseEntity<Review> editReview(@RequestBody ReviewDTO reviewDTO, @PathVariable long reviewId ){
        Optional<Review> targetReview = reviewService.getReviewById(reviewId);

        if(targetReview.isPresent()) {
        Review updatedReview = reviewService.editReview(reviewDTO, reviewId);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteReview(@PathVariable long id){
        reviewService.deleteReview(id);
        return new ResponseEntity<>(id,HttpStatus.NO_CONTENT);
    }


}
