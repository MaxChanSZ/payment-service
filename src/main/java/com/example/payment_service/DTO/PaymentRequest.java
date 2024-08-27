package com.example.payment_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentRequest {

    private String paymentRequestID;
    private String paymentDetails;
    private String paymentType;
    private Long paymentAmount;
    private String userAccountID;
    private String stripeEmail;
    private String stripeToken;
    private String currency;

    @Override
    public String toString() {
        return "PaymentRequest{"
                + "paymentRequestID='" + paymentRequestID + '\''
                + ", paymentDetails='" + paymentDetails + '\''
                + ", paymentType='" + paymentType + '\''
                + ", paymentAmount='" + paymentAmount + '\''
                + ", userAccountID='" + userAccountID + '\''
                + ", stripeEmail='" + stripeEmail + '\''
                + ", stripeToken='" + stripeToken + '\''
                + ", currency='" + currency + '\''
                + "}";
    }

}
