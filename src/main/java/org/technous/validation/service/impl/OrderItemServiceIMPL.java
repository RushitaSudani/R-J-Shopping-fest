package org.technous.validation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.technous.validation.model.OrderItem;
import org.technous.validation.repository.OrderItemRepository;
import org.technous.validation.service.OrderItemService;

@Service
public class OrderItemServiceIMPL implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
