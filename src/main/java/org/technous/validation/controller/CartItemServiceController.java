package org.technous.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.technous.validation.exception.CartItemException;
import org.technous.validation.exception.UserException;
import org.technous.validation.model.CartItem;
import org.technous.validation.service.CartItemService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CartItemServiceController {

    @Autowired
    private CartItemService cartItemService;
    @PostMapping("/createCartItem/{cartId}")
    public CartItem createCartItem(@RequestBody CartItem cartItem, @PathVariable("cartId") Long cartId){
        CartItem mCartItem = cartItemService.createCartItem(cartItem,cartId);
        return mCartItem;
    }

    @DeleteMapping("/removeItem/{cartItemId}")
    public String removeItemFromCart(@Param("userId") Long userId,
                                     @PathVariable("cartItemId") Long cartItemId)
            throws CartItemException, UserException {
        cartItemService.removeCartItem(userId,cartItemId);
        return "Item Remove From the Cart  ::"+cartItemId;
    }


}
