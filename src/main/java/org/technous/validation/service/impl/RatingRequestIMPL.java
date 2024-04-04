package org.technous.validation.service.impl;

import org.springframework.stereotype.Service;
import org.technous.validation.exception.ProductException;
import org.technous.validation.model.Product;
import org.technous.validation.model.Rating;
import org.technous.validation.model.User;
import org.technous.validation.repository.RatingRepository;
import org.technous.validation.request.RatingRequest;
import org.technous.validation.service.ProductService;
import org.technous.validation.service.RatingService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingRequestIMPL implements RatingService {

    private RatingRepository ratingRepository;
    private ProductService productService;
    public RatingRequestIMPL(RatingRepository ratingRepository,
                             ProductService productService){
        this.ratingRepository=ratingRepository;
        this.productService=productService;
    }
    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product = productService.findByProductId(req.getProductId());
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setProduct(product);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }
    @Override
    public List<Rating> getProductRating(Long productId) {
        List<Rating> ratingList = ratingRepository.getAllProductRating(productId);
        return ratingList;
    }
}
