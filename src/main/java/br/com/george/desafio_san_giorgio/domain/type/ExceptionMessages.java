package br.com.george.desafio_san_giorgio.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {

    SELLER_NOT_FOUND("Seller %s not found"),
    PAYMENT_NOT_FOUND("Payment %s not found");

    private final String message;
}
