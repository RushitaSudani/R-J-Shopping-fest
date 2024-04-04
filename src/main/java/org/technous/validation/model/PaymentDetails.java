package org.technous.validation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDetails {
    private String paymentMethod;
    private String status;
    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferenceId;
    private String razorpayPaymentLinkStatus;
    private String razorPaymentId;

    public PaymentDetails(String paymentMethod, String status, String paymentId, String razorpayPaymentLinkId, String razorpayPaymentLinkReferenceId, String razorpayPaymentLinkStatus, String razorPaymentId) {
        super();
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentId = paymentId;
        this.razorpayPaymentLinkId = razorpayPaymentLinkId;
        this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
        this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
        this.razorPaymentId = razorPaymentId;
    }
    public PaymentDetails() {
        super();
    }
}
