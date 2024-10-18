package br.com.george.desafio_san_giorgio.infrastructure.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record PaymentItemRequest(
        @JsonProperty("payment_id")
        String code,

        @JsonProperty("payment_value")
        BigDecimal amount
) {
}
