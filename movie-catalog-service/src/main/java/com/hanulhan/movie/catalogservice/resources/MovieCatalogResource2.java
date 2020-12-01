/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hanulhan.movie.catalogservice.resources;

import com.hanulhan.movie.catalogservice.models.CatalogItem;
import com.hanulhan.movie.catalogservice.models.Movie;
import com.hanulhan.movie.catalogservice.models.Rating;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author uli Example with WebClient
 *
 */
@RestController
@RequestMapping("/catalog2")
public class MovieCatalogResource2 {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        // get all rated movie ID's
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );

        // for each movie id, call movie info service and get details
        // put all together
        return ratings.stream().map(rating -> {
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/\" + rating.getMovieId()")
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

            return new CatalogItem(movie.getName(), "Description", rating.getRating());
        }
        ).collect(Collectors.toList());

        // return hard coded
//        return Collections.singletonList(
//                new CatalogItem("Transformers", "Test", 4)
//        );
    }

}
