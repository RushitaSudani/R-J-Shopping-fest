package org.technous.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("removeItem/{userId}/{cartItemId}")
    public String removeItemFromCart(@PathVariable("userId") Long userId,@PathVariable("cartItemId") Long cartItemId) throws CartItemException, UserException {
        cartItemService.removeCartItem(userId,cartItemId);
        return "Item Remove From the Cart  ::"+cartItemId;
    }


}
