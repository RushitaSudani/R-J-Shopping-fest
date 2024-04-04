package org.technous.validation.service;

import org.technous.validation.exception.ProductException;
import org.technous.validation.model.Review;
import org.technous.validation.model.User;
import org.technous.validation.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest req, User user) throws ProductException;
    public List<Review> getAllReview(Long productId) throws ProductException;

}
