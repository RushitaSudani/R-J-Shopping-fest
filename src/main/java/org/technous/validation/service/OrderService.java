package org.technous.validation.service;

import org.technous.validation.exception.OrderException;
import org.technous.validation.model.Address;
import org.technous.validation.model.Order;
import org.technous.validation.model.User;

import java.util.List;

public interface OrderService {
        public Order createOrder(User user, Address shippingAddress);
        public Order findOrderById(Long orderId) throws OrderException;
        public List<Order> usersOrderHistory(Long userId);
        public Order placeOrder(Long orderId) throws OrderException;
        public Order confirmedOrder(Long orderId) throws OrderException;
        public Order shippedOrder(Long orderId) throws OrderException;
        public Order deliverdOrder(Long orderId) throws OrderException;
        public Order cancleOrder(Long orderId)throws OrderException;
        public List<Order> getAllOrders();
        public void deleteOrder(Long orderId)throws OrderException;
}
