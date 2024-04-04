package org.technous.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.technous.validation.exception.ProductException;
import org.technous.validation.exception.UserException;
import org.technous.validation.model.Review;
import org.technous.validation.model.User;
import org.technous.validation.request.ReviewRequest;
import org.technous.validation.service.ReviewService;
import org.technous.validation.service.UserService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    public ReviewService reviewService;
    @Autowired
    private UserService userService;


    @PostMapping("/saveReview/{userId}")
    public ResponseEntity<Review> saveRate(@RequestBody ReviewRequest reviewRequest, @PathVariable("userId") Long userId) throws ProductException, UserException {
        User user = userService.findUserById(userId);
        Review review = reviewService.createReview(reviewRequest, user);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/getReviewByPid/{productId}")
    public ResponseEntity<List<Review>> getReviewByProductId(@PathVariable("productId") Long productId) throws ProductException {
        List<Review> reviews = reviewService.getAllReview(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
