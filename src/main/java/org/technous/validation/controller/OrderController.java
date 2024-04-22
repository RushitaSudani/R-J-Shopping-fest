package org.technous.validation.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.technous.validation.exception.OrderException;
import org.technous.validation.exception.UserException;
import org.technous.validation.model.Address;
import org.technous.validation.model.Order;
import org.technous.validation.model.User;
import org.technous.validation.service.Iuserservice;
import org.technous.validation.service.OrderService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private Iuserservice userService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrdersHandler(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@Param("userId") Long userId,
                                             @RequestBody Address shippedaddress) throws UserException {
        User user = userService.findUserById(userId);
        Order order = orderService.createOrder(user,shippedaddress);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/getOrderById/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Long orderId) throws OrderException {
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/getOrderHistory/{orderId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable("orderId") Long orderId){
        List<Order> orders = orderService.usersOrderHistory(orderId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/admin/orders/{orderId}/placed")
    public ResponseEntity<Order> placeOrder(@PathVariable("orderId") Long orderId) throws OrderException {
        Order order = orderService.placeOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/admin/orders/{orderId}/confirmed")
    public ResponseEntity<Order> confirmOrder(@PathVariable("orderId") Long orderId) throws OrderException {
        Order order = orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/admin/orders/{orderId}/ship")
    public ResponseEntity<Order> shippedOrder(@PathVariable("orderId") Long orderId) throws OrderException {
        Order order = orderService.shippedOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PutMapping("/admin/orders/{orderId}/deliver")
    public ResponseEntity<Order> deliveredOrder(@PathVariable("orderId") Long orderId) throws OrderException {
        Order order = orderService.deliverdOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PutMapping("/admin/orders/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable("orderId") Long orderId) throws OrderException {
        Order order = orderService.cancleOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Operation(
            tags = "Get Order",
            description = "get All Orders",
            responses = {
                    @ApiResponse(
                            description="Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "not Okay",
                            responseCode = "500"
                    )
            }
    )
    @GetMapping("/admin/orders")
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @DeleteMapping("/admin/orders/{orderId}/delete")
    public String deleteOrder(@PathVariable("orderId") Long orderId) throws OrderException {
        orderService.deleteOrder(orderId);
        return "Your Ordered Is Deleted";
    }

}
