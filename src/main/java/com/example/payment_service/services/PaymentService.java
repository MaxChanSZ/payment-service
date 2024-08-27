package com.example.payment_service.services;

import com.example.payment_service.DTO.PaymentRequest;
import com.example.payment_service.model.PaymentTransaction;
import com.example.payment_service.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    @Value("${STRIPE_SECRET_KEY}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    @Autowired
    private PaymentRepository paymentRepository;

    public Charge processStripePayment(PaymentRequest paymentRequest) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("currency", "SGD");
        chargeParams.put("amount", paymentRequest.getPaymentAmount());
        chargeParams.put("description", paymentRequest.getPaymentDetails());
        chargeParams.put("source", paymentRequest.getStripeToken());
        return Charge.create(chargeParams);
    }

    public void updateLedger(Charge stripePaymentResponse, String transactionType) {
        Long transactionAmount = stripePaymentResponse.getAmount();
        String walletID = UUID.randomUUID().toString(); //Mocking for walletID to be implemented.

        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setTransactionType(transactionType);
        paymentTransaction.setTransactionAmount(transactionAmount);
        paymentTransaction.setWalletID(walletID);
        paymentRepository.save(paymentTransaction);

    }
}
