package br.com.george.desafio_san_giorgio.factory;

import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.domain.entity.PaymentConfirmation;

import java.util.List;
import java.util.UUID;

public class PaymentConfirmationFactory {

    private PaymentConfirmationFactory() {

    }

    public static PaymentConfirmation create(Payment payment) {
        return PaymentConfirmation.builder()
                .payments(List.of(payment))
                .sellerCode(UUID.randomUUID().toString())
                .build();
    }
}
