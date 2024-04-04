package org.technous.validation.service;

import org.technous.validation.exception.CartItemException;
import org.technous.validation.exception.UserException;
import org.technous.validation.model.Cart;
import org.technous.validation.model.CartItem;
import org.technous.validation.model.Product;
import org.technous.validation.service.impl.CartItemServiceIMPL;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws CartItemException, UserException;
    public CartItem isCartItemExits(Cart cart, Product product,String size,Long userId);
    public void removeCartItem(Long userId,Long cartItemId) throws UserException, CartItemException;
    public CartItem findCartItemById(Long cartItemID) throws CartItemException;

    public CartItem createCartItem(CartItem cartItem,Long cartId);


}
