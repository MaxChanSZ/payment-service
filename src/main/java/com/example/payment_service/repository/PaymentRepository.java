package com.example.payment_service.repository;

import com.example.payment_service.model.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentTransaction, Long> {
}
