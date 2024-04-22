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
import org.technous.validation.request.UpdateCartItemDTO;
import org.technous.validation.service.CartService;
import org.technous.validation.service.impl.UserService;

import java.util.List;

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
    @PutMapping("/cart/add/{productId}")
    public String addItemToCart(@RequestBody AddItemRequest req,
                                @Param("userId") Long userId,
                                @PathVariable("productId") Long productId) {
        cartService.addCartItem(userId, req ,productId);
        return "Item Addedd to Cart"+userId;
    }

    @GetMapping("/findByUserId/{userId}")
   // @Operation(description = "find by user id")
    public ResponseEntity<Cart> findCartByUserId(@PathVariable("userId") Long userId) {
        Cart cart = cartService.findUserCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/findByCartId/{cartId}")
    public ResponseEntity<Cart> findByCart(@PathVariable("cartId") Long cartId) throws CartItemException {
        Cart cart = cartService.findByCartId(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/getAllCart")
    public ResponseEntity<List<Cart>> findAll(){
        List<Cart> carts = cartService.getAllCart();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
//    @PutMapping("/{cartId}/updateCartItem")
//    public ResponseEntity<String> updateCartItem(@PathVariable("cartId") Long cartId,
//                                                 @RequestBody UpdateCartItemDTO updateDTO,
//                                                 @Param("userId") Long userId) {
//        try {
//            cartService.updateCart(updateDTO,userId);
//            return ResponseEntity.ok("Cart item updated successfully");
//        } catch (CartItemException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
    @PutMapping("/updateCartItem")
    public ResponseEntity<String> updateCartItem(
                                                 @RequestBody UpdateCartItemDTO updateDTO,
                                                 @Param("userId") Long userId) {
        try {
            cartService.updateCart(updateDTO,userId);
            return ResponseEntity.ok("Cart item updated successfully");
        } catch (CartItemException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> removeCart(@PathVariable("cartId") Long cartId) {
        try {
            cartService.removeCart(cartId);
            return ResponseEntity.ok("Cart removed successfully");
        } catch (CartItemException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
