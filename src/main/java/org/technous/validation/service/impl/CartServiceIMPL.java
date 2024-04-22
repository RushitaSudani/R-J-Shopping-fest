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
import org.technous.validation.request.UpdateCartItemDTO;
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

//    @Override
//    public Cart updateCart(Long cartId, UpdateCartItemDTO updateDTO) throws CartItemException {
//
//        Optional<Cart> optionalCart = cartRepository.findById(cartId);
//        if (optionalCart.isPresent()) {
//            Cart cart = optionalCart.get();
//            Optional<CartItem> optionalCartItem = cart.getCartItems().stream()
//                    .filter(item -> item.getId() == updateDTO.getCartItemId())
//                    .findFirst();
//            if (optionalCartItem.isPresent()) {
//                CartItem cartItemToUpdate = optionalCartItem.get();
//                cartItemToUpdate.setQuantity(updateDTO.getQuantity());
//                return cartRepository.save(cart);
//            } else {
//                throw new CartItemException("Cart item not found");
//            }
//        } else {
//            throw new CartItemException("Cart not found");
//        }
//    }

//    @Override
//    public Cart updateCart( UpdateCartItemDTO updateDTO,Long userId) throws CartItemException {
//
//        Optional<Cart> optionalCart = Optional.ofNullable(cartRepository.findByUserId(userId));
//
//        //Optional<Cart> optionalCart = cartRepository.findByUserId(cartId);
//        if (optionalCart.isPresent()) {
//            Cart cart = optionalCart.get();
//            Optional<CartItem> optionalCartItem = cart.getCartItems().stream()
//                    .filter(item -> item.getId() == updateDTO.getCartItemId())
//                    .findFirst();
//            if (optionalCartItem.isPresent()) {
//                CartItem cartItemToUpdate = optionalCartItem.get();
//                cartItemToUpdate.setQuantity(updateDTO.getQuantity());
//                return cartRepository.save(cart);
//            } else {
//                throw new CartItemException("Cart item not found");
//            }
//        } else {
//            throw new CartItemException("Cart not found");
//        }
//    }

    @Override
    public Cart updateCart(UpdateCartItemDTO updateDTO, Long userId) throws CartItemException {
        Cart cart = cartRepository.findByUserId(userId);
               // .orElseThrow(() -> new CartItemException("Cart not found"));

        CartItem cartItemToUpdate = cart.getCartItems().stream()
                .filter(item -> item.getId() == updateDTO.getCartItemId())
                .findFirst()
                .orElseThrow(() -> new CartItemException("Cart item not found"));

        cartItemToUpdate.setQuantity(updateDTO.getQuantity());
        return cartRepository.save(cart);
    }



    @Override
    public void removeCart(Long cartId) throws CartItemException {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            cartRepository.delete(optionalCart.get());
        } else {
            throw new CartItemException("Cart not found");
        }
    }
}
