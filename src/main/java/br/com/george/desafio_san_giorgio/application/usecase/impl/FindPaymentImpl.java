package br.com.george.desafio_san_giorgio.application.usecase.impl;

import br.com.george.desafio_san_giorgio.application.gateway.PaymentGateway;
import br.com.george.desafio_san_giorgio.application.usecase.FindPayment;
import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.domain.exception.PaymentNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static br.com.george.desafio_san_giorgio.domain.type.ExceptionMessages.PAYMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FindPaymentImpl implements FindPayment {

    private final PaymentGateway paymentGateway;

    @Override
    public Payment execute(String code) {
        Payment payment = this.paymentGateway.findPaymentByCode(code);
        if (Objects.isNull(payment))
            throw new PaymentNotFound(String.format(PAYMENT_NOT_FOUND.getMessage(), code));
        return payment;
    }
}
