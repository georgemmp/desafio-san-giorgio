package br.com.george.desafio_san_giorgio.factory;

import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.domain.type.StatusPayment;

import java.math.BigDecimal;

public class SellerFactory {

    private SellerFactory() {}

    public static Payment create(String code) {
        return Payment.builder()
                .code(code)
                .statusPayment(StatusPayment.FULL)
                .value(BigDecimal.valueOf(100))
                .build();
    }
}
