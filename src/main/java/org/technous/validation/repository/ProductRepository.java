package org.technous.validation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.technous.validation.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT P FROM Product P " +
            "WHERE (:minPrice IS NULL AND :maxPrice IS NULL) OR (P.discountedPrice BETWEEN :minPrice AND :maxPrice) " +
            "AND (:minDiscount IS NULL OR P.discountedPersent >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN P.discountedPrice END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN P.discountedPrice END DESC")
    List<Product> filterProducts(@Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice,
                                 @Param("minDiscount") Integer minDiscount,
                                 @Param("sort") String sort);
    @Query("SELECT P FROM Product P " +
            "WHERE (:minPrice IS NULL AND :maxPrice IS NULL) OR (P.discountedPrice BETWEEN :minPrice AND :maxPrice) " +
            "AND (:minDiscount IS NULL OR P.discountedPersent >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN P.discountedPrice END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN P.discountedPrice END DESC")
    List<Product> filterProductss(@Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice,
                                 @Param("minDiscount") Integer minDiscount,
                                 @Param("sort") String sort);

}
