package com.example.payment_service.API_index;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseStripe extends ApiResponse {
    private String cardName;
    private LocalDateTime transactionDate;
}
