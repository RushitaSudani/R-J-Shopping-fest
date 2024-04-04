package org.technous.validation.service;

import org.technous.validation.exception.ProductException;
import org.technous.validation.model.Rating;
import org.technous.validation.model.User;
import org.technous.validation.request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating createRating(RatingRequest req, User user)throws ProductException;
    public List<Rating> getProductRating(Long productId);
}
