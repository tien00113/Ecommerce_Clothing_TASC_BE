package com.tasc.clothing.service;

import com.tasc.clothing.model.Payment;

public interface paymentservice {
    public Payment createPayment(Payment payment);
    public Payment deletePayment(Payment payment , Long payment_id);
    public Payment ediPayment(Payment payment , Long payment_id);
}
