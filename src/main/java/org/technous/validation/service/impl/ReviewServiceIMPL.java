package org.technous.validation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.technous.validation.exception.ProductException;
import org.technous.validation.model.Product;
import org.technous.validation.model.Review;
import org.technous.validation.model.User;
import org.technous.validation.repository.ReviewRepository;
import org.technous.validation.request.ReviewRequest;
import org.technous.validation.service.ProductService;
import org.technous.validation.service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ReviewServiceIMPL implements ReviewService {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product = productService.findByProductId(req.getProductId());
        Review review = new Review();
        review.setUser(user);
        review.setCreatedAt(LocalDateTime.now());
        review.setProduct(product);
        review.setReview(review.getReview());
        return reviewRepository.save(review);
    }
    @Override
    public List<Review> getAllReview(Long productId) throws ProductException {

        List<Review> reviews = reviewRepository.getAllProductsReview(productId);
        return reviews;
    }
}
