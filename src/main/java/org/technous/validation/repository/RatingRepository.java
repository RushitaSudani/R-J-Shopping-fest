package org.technous.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.technous.validation.model.Product;
import org.technous.validation.model.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    @Query("select r from Rating r where r.product.id=:productId")
    public List<Rating> getAllProductRating(@Param("productId") Long productId);
}
