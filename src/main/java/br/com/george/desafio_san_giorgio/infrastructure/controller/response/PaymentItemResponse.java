package br.com.george.desafio_san_giorgio.infrastructure.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record PaymentItem(
        @JsonProperty("payment_id")
        String code,

        @JsonProperty("payment_value")
        BigDecimal amount,

        @JsonProperty("amount_paid")
        BigDecimal amountPaid,

        @JsonProperty("payment_status")
        String paymentStatus
) {
}
