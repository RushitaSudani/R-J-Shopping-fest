package org.technous.validation.service;
import org.technous.validation.exception.CartItemException;
import org.technous.validation.model.Cart;
import org.technous.validation.model.User;
import org.technous.validation.request.AddItemRequest;
public interface CartService {

    public Cart createCart(User user);
    public String addCartItem(Long userId, AddItemRequest req,Long productId);
    public Cart findUserCart(Long userId);
    public Cart findByCartId(Long cartId) throws CartItemException;
}
