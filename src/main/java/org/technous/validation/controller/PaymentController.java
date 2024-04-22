package org.technous.validation.controller;

import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.technous.validation.dto.OrderDto;
import org.technous.validation.service.PaymentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<?> pay() {
        try
        {
            OrderDto orderDTO = paymentService.pay();
            return ResponseEntity.ok(orderDTO);
        } catch (RazorpayException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing payment: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePay(){
        try {

            return null;
        }catch (Exception e){
            return null;
        }
    }



}
