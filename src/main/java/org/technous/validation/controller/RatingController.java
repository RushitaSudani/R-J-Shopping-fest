package org.technous.validation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.technous.validation.exception.ProductException;
import org.technous.validation.exception.UserException;
import org.technous.validation.model.Rating;
import org.technous.validation.model.User;
import org.technous.validation.request.RatingRequest;
import org.technous.validation.service.Iuserservice;
import org.technous.validation.service.RatingService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private Iuserservice userService;
    @PostMapping("/saveRate/{userId}")
    public ResponseEntity<Rating> saveRate(@RequestBody RatingRequest ratingRequest, @PathVariable("userId") Long userId)
            throws ProductException, UserException {
        User user = userService.findUserById(userId);
        Rating rating = ratingService.createRating(ratingRequest,user);
       return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping("getRatingByPid/{productId}")
    public ResponseEntity<List<Rating>> getRatingsByProductId(@PathVariable("productId") Long productId){
        List<Rating> ratings = ratingService.getProductRating(productId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
}
