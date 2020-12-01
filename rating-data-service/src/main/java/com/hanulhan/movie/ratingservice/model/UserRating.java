/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hanulhan.movie.ratingservice.model;

import java.util.List;

/**
 *
 * @author uli
 */
public class UserRating {
    private List<Rating> userRating;

    public UserRating() {
        this.userRating = userRating;
    }

    public UserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }

    
    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }

    
}
