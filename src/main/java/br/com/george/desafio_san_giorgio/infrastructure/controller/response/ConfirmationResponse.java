package br.com.george.desafio_san_giorgio.infrastructure.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ConfirmationResponse(
        @JsonProperty("seller_id")
        String sellerCode,

        @JsonProperty("payment_items")
        List<PaymentItemResponse> paymentItemResponses
) {
}
