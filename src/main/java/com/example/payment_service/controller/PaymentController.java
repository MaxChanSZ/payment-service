package com.example.payment_service.controller;

import com.example.payment_service.API_index.ApiResponse;
import com.example.payment_service.API_index.ApiResponseStripe;
import com.example.payment_service.DTO.PaymentRequest;
import com.example.payment_service.services.PaymentService;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/stripeCharge")
    public ApiResponseStripe paymentByStripe(@RequestBody PaymentRequest paymentRequest) throws APIConnectionException, APIException, AuthenticationException, InvalidRequestException, CardException {
        ApiResponseStripe response = new ApiResponseStripe();
        paymentRequest.setPaymentDetails("Stripe Payment - Sample");
        paymentRequest.setCurrency("SGD");
        Charge stripePaymentRequest = paymentService.processStripePayment(paymentRequest);
        if (stripePaymentRequest.getStatus().equals("succeeded")) {
            paymentService.updateLedger(stripePaymentRequest, "Stripe Transaction");
        }
        response.setCardName(stripePaymentRequest.getCustomer());
        response.setStatus(stripePaymentRequest.getStatus());
        response.setTransactionDate(LocalDateTime.now());
        

        return response;
    }
}
