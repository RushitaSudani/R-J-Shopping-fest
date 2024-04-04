package org.technous.validation.model;
import jakarta.persistence.Embeddable;

@Embeddable
public class PaymentInformation {
    private String cardholderName;
    private String cardNumber;
    private String expirationDate;
    private String cvv;
}
