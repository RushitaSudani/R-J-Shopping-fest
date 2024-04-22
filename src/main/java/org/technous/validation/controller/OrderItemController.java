package org.technous.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.technous.validation.model.OrderItem;
import org.technous.validation.service.OrderItemService;

@RestController
@RequestMapping("/api")
public class OrderItemController {

    @Autowired
    public OrderItemService orderItemService;

    @PostMapping("/createOrderItem")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem orderItem1 = orderItemService.createOrderItem(orderItem);
        return new ResponseEntity<>(orderItem1, HttpStatus.OK);
    }


}
