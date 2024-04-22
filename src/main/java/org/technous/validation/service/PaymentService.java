package org.technous.validation.service;

import com.razorpay.RazorpayException;
import org.technous.validation.dto.OrderDto;

public interface PaymentService {
    OrderDto pay() throws RazorpayException;
}
