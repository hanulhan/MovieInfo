/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hanulhan.movie.catalogservice.resources;

import com.hanulhan.movie.catalogservice.models.CatalogItem;
import com.hanulhan.movie.catalogservice.models.Movie;
import com.hanulhan.movie.catalogservice.models.Rating;
import com.hanulhan.movie.catalogservice.models.UserRating;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author uli
 */
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        // get all rated movie ID's
        // hardcoded
//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 3)
//        );
        
        //UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
        
        // Get url information from Eureka server
        UserRating userRating = restTemplate.getForObject("http://rating-service/ratingsdata/users/" + userId, UserRating.class);

        return userRating.getUserRating().stream().map(rating -> {
            // for each movie id, call movie info service and get details
            //Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            Movie movie = restTemplate.getForObject("http://info-service/movies/" + rating.getMovieId(), Movie.class);

            // put all together
            // Hard coded
            // return new CatalogItem("Transformers", "Test", 4);
            return new CatalogItem(movie.getName(), "Description", rating.getRating());
        }
        ).collect(Collectors.toList());

        // return hard coded
//        return Collections.singletonList(
//                new CatalogItem("Transformers", "Test", 4)
//        );
    }

}
