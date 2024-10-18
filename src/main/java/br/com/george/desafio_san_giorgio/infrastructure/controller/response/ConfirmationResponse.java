package br.com.george.desafio_san_giorgio.infrastructure.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Confirmation(
        @JsonProperty("seller_id")
        String sellerCode,

        @JsonProperty("payment_items")
        List<PaymentItem> paymentItems
) {
}
