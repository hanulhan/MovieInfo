/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hanulhan.movie.ratingservice.resources;

import com.hanulhan.movie.ratingservice.model.Rating;
import com.hanulhan.movie.ratingservice.model.UserRating;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author uli
 */
@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
    
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId)    {
        return new Rating(movieId, 4);
    }
    
    // recommendet to to this
    // Problem: an API returning a list as the root node
    // serializationi of a list is a challange
    // enhancement is not possible easily. An object would be necessary.
    
    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId)    {
        List<Rating> ratings= Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );
        
        UserRating userRating = new UserRating(ratings);

        return userRating;
    }
}
