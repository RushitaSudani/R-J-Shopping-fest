package org.technous.validation.service.impl;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.technous.validation.dto.OrderDto;
import org.technous.validation.repository.OrderRepository;
import org.technous.validation.service.PaymentService;

@Service
public class PaymentServiceIMPL implements PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    @Value("${razorpay.api.key}")
    String apiKey;
    @Value("${razorpay.api.secret}")
    String apiSecret;
    @Override
    public OrderDto pay() throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", 12000);
        paymentLinkRequest.put("currency", "INR");
        try {
            Order order = razorpayClient.orders.create(paymentLinkRequest);
            OrderDto orderDTO = new OrderDto();
            orderDTO.setId(order.get("id"));
            orderDTO.setAmount(order.get("amount"));
            orderDTO.setCurrency(order.get("currency"));
            return orderDTO;
        } catch (RazorpayException e) {
            throw new RazorpayException("Error processing payment: " + e.getMessage());
        }
    }
//    public OrderDto payy(Long orderId) throws RazorpayException {
//        // Fetch the order from the database
//        OrderEntity orderEntity = orderRepository.findById(orderId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + orderId));
//
//        int totalAmount = orderEntity.getTotalPrice(); // Assume getTotalPrice returns amount in paise
//
//        RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);
//        JSONObject paymentLinkRequest = new JSONObject();
//        paymentLinkRequest.put("amount", totalAmount); // Use dynamic value from order
//        paymentLinkRequest.put("currency", "INR");
//
//        try {
//            Order razorpayOrder = razorpayClient.orders.create(paymentLinkRequest);
//            OrderDto orderDTO = new OrderDto();
//            orderDTO.setId(razorpayOrder.get("id"));
//            orderDTO.setAmount(razorpayOrder.get("amount"));
//            orderDTO.setCurrency(razorpayOrder.get("currency"));
//            return orderDTO;
//        } catch (RazorpayException e) {
//            throw new RazorpayException("Error processing payment: " + e.getMessage());
//        }
//    }
}
