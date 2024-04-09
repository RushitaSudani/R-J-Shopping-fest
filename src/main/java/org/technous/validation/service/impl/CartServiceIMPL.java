package org.technous.validation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.technous.validation.exception.CartItemException;
import org.technous.validation.model.Cart;
import org.technous.validation.model.CartItem;
import org.technous.validation.model.Product;
import org.technous.validation.model.User;
import org.technous.validation.repository.CartRepository;
import org.technous.validation.request.AddItemRequest;
import org.technous.validation.service.CartItemService;
import org.technous.validation.service.CartService;
import org.technous.validation.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceIMPL implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;
    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }
    @Override
    public String addCartItem(Long userId, AddItemRequest req,Long productId) {
        Cart cart =cartRepository.findByUserId(userId);
        Product product = productService.findByProductId(productId);
        CartItem isPresent = cartItemService.isCartItemExits(cart,product, req.getSize(),userId);
        if(isPresent==null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);
            int price = req.getQuantity() * product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());
            CartItem createCartItem =cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createCartItem);
        }
        return "Item Addedd to Cart";
    }

    public String addCartItemm(Long userId, AddItemRequest req,Long productId) {
        Cart cart =cartRepository.findByUserId(userId);
        Product product = productService.findByProductId(productId);
        CartItem isPresent = cartItemService.isCartItemExits(cart,product, req.getSize(),userId);
        if(isPresent==null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            //  cartItem.setUserId(userId);
            int price =req.getQuantity() * product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());
            CartItem createCartItem =cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createCartItem);
        }
        return "Item Addedd to Cart";
    }
    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);

        //System.out.println(cart.getCartItems());
        int totalPrice = 0 ;
        int totalItem = 0 ;
        int totaldiscountPrice = 0;

        for(CartItem cartItem:cart.getCartItems()){
            totalPrice+= cartItem.getPrice();
            totaldiscountPrice+= cart.getDiscountedPrice();
            totalItem+= cart.getTotalItem();
        }
        cart.setDiscountedPrice(totaldiscountPrice);
        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setDiscounte(totalPrice - totaldiscountPrice);

        return cartRepository.save(cart);
    }
    @Override
    public Cart findByCartId(Long cartId) throws CartItemException {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isPresent()){
            return cart.get();
        }
        else
        {
            throw new CartItemException("Cart not here...");
        }
    }

    @Override
    public List<Cart> getAllCart() {

        List<Cart> carts = cartRepository.findAll();
        return carts;
    }
}
