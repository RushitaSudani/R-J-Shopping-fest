package org.technous.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.technous.validation.model.Cart;
import org.technous.validation.model.CartItem;
import org.technous.validation.model.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Query("select c from CartItem c where c.cart=:cart AND c.product=:product and c.size=:size and c.userId=:userId")
    public CartItem isCartItemExits(@Param("cart") Cart cart,
                                    @Param("product")Product product,
                                    @Param("size") String size,
                                    @Param("userId") Long userId);

}
