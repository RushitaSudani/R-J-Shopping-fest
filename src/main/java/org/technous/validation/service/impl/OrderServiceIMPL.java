package org.technous.validation.service.impl;

import org.springframework.stereotype.Service;
import org.technous.validation.exception.OrderException;
import org.technous.validation.model.*;
import org.technous.validation.repository.AddressRepository;
import org.technous.validation.repository.OrderItemRepository;
import org.technous.validation.repository.OrderRepository;
import org.technous.validation.repository.UserRepository;
import org.technous.validation.service.CartService;
import org.technous.validation.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceIMPL implements OrderService {
    private OrderRepository orderRepository;
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private OrderItemRepository orderItemRepository;
    private CartService cartService;

    public OrderServiceIMPL(
            CartService cartService,
            OrderRepository orderRepository,
            AddressRepository addressRepository,
            UserRepository userRepository,
            OrderItemRepository orderItemRepository){
        this.cartService = cartService;
        this.orderRepository=orderRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.orderItemRepository=orderItemRepository;
    }
    @Override
    public Order createOrder(User user, Address shippingAddress) {
        shippingAddress.setUser(user);
        Address address = addressRepository.save(shippingAddress);
        user.getAddessList().add(address);
        userRepository.save(user);

        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem cartItem:cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSize(cartItem.getSize());
            orderItem.setUserId(cartItem.getUserId());
            orderItem.setDiscountedPrice(cartItem.getDiscountedPrice());

            OrderItem createdOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(createdOrderItem);
        }
        Order createOrder = new Order();
        createOrder.setUser(user);
        createOrder.setOrderItems(orderItems);
        createOrder.setTotalPrice(cart.getTotalPrice());
        createOrder.setTotalDiscountedPrice(cart.getDiscountedPrice());
        createOrder.setDiscounte(cart.getDiscounte());
        createOrder.setTotalItem(cart.getTotalItem());
        createOrder.setShippingAddress(address);

        createOrder.setOrderStatus("PENDING");
      //  createOrder.getPaymentDetails().setStatus("PENDING");
        createOrder.setCreateAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(createOrder);
        for(OrderItem orderItem:orderItems){
            orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
        }
        return savedOrder;
    }
    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()){
            return order.get();
        }
        throw new OrderException("Order not Exits with This orderId"+ orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        List<Order> orders = orderRepository.getUsersOrders(userId);
        return orders;
    }

    @Override
    public Order placeOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("PLACED");
        //order.getPaymentDetails().setStatus("COMPLETE");
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");
        return orderRepository.save(order);
    }
    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public Order deliverdOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("DELIVERED");
        return orderRepository.save(order);
    }

    @Override
    public Order cancleOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CANCLED");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);

    }
}
