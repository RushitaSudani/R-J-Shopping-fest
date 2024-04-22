package org.technous.validation.service.impl;
import org.springframework.stereotype.Service;
import org.technous.validation.exception.CartItemException;
import org.technous.validation.exception.UserException;
import org.technous.validation.model.Cart;
import org.technous.validation.model.CartItem;
import org.technous.validation.model.Product;
import org.technous.validation.model.User;
import org.technous.validation.repository.CartItemRepository;
import org.technous.validation.repository.CartRepository;
import org.technous.validation.service.CartItemService;

import java.util.Optional;
import java.util.Set;

@Service
public class CartItemServiceIMPL implements CartItemService {

    private CartRepository cartRepository;
    private UserService userService;
    private CartItemRepository cartItemRepository;
    public CartItemServiceIMPL(CartItemRepository cartItemRepository,
                               UserService userService,
                               CartRepository cartRepository){
        this.cartItemRepository=cartItemRepository;
        this.cartRepository=cartRepository;
        this.userService=userService;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem, Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cartItem.setQuantity(1);
       // cartItem.setProduct(cartItem.getProduct();
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());
        CartItem createItem = cartItemRepository.save(cartItem);
        cart.setCartItems((Set<CartItem>) createItem);
        return createItem;
    }
    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());
        CartItem createCartItem =cartItemRepository.save(cartItem);
        return createCartItem;
    }
    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem cartItem1 = findCartItemById(userId);
        User user = userService.findUserById(userId);
        if(user.getId()==userId){
            cartItem1.setQuantity(cartItem.getQuantity());
            cartItem1.setPrice(cartItem.getQuantity()*cartItem.getProduct().getPrice());
            cartItem1.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());
        }
        return cartItemRepository.save(cartItem1);
    }
    @Override
    public CartItem isCartItemExits(Cart cart, Product product, String size, Long userId) {
        CartItem cartItem = cartItemRepository.isCartItemExits(cart,product,size,userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws UserException, CartItemException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());
        User reqUser = userService.findUserById(userId);
        if(user.getId()== reqUser.getId()){
            cartItemRepository.deleteById(cartItemId);
        }else{
            throw new CartItemException("Your not removed Another User Item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemID) throws CartItemException {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemID);
        if(cartItem.isPresent()){
            return cartItem.get();
        }
        else {
            throw new CartItemException("Cart Item not Found With Id"+ cartItemID);
        }
    }


}
