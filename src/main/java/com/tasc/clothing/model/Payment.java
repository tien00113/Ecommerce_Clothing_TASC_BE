package com.tasc.clothing.model;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String method; // Phương thức thanh toán, ví dụ: tiền mặt, thẻ tín dụng, chuyển khoản
    private int amount; // Số tiền thanh toán
    private String payer; // Người thanh toán
    private Date paymentDate; // Ngày thanh toán
    private String status; 
    
}
