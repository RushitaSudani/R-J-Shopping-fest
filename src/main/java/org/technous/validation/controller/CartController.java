package org.technous.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.technous.validation.exception.CartItemException;
import org.technous.validation.exception.UserException;
import org.technous.validation.model.Cart;
import org.technous.validation.model.User;
import org.technous.validation.request.AddItemRequest;
import org.technous.validation.service.CartService;
import org.technous.validation.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @PostMapping("/createCart/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable("userId") Long userId) throws UserException {
        User user = userService.findUserById(userId);
        Cart cart = cartService.createCart(user);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    @PostMapping("/cart/add/{productId}/{userId}")
    public String addItemToCart(@RequestBody AddItemRequest req, @PathVariable("productId") Long productId ,
                                @PathVariable("userId")Long userId) {
        cartService.addCartItem(userId, req,productId);
        return "Item Addedd to Cart";
    }

    @GetMapping("/findByUserId/{userId}")
    public ResponseEntity<Cart> findCartByUserId(@Param("userId") Long userId) {
        Cart cart = cartService.findUserCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/findByCartId/{cartId}")
    public ResponseEntity<Cart> findByCart(@Param("cartId") Long cartId) throws CartItemException {
        Cart cart = cartService.findByCartId(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
