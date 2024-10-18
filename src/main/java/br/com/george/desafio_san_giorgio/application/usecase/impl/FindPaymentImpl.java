package br.com.george.desafio_san_giorgio.application.usecase.impl;

import br.com.george.desafio_san_giorgio.application.gateway.PaymentGateway;
import br.com.george.desafio_san_giorgio.application.usecase.FindPayment;
import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.domain.exception.PaymentNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static br.com.george.desafio_san_giorgio.domain.type.ExceptionMessages.PAYMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindPaymentImpl implements FindPayment {

    private final PaymentGateway paymentGateway;

    @Override
    public Payment execute(String code) {
        log.info("Looking for payment {}", code);
        Payment payment = this.paymentGateway.findPaymentByCode(code);
        if (Objects.isNull(payment))
            throw new PaymentNotFound(String.format(PAYMENT_NOT_FOUND.getMessage(), code));
        log.info("Payment found {}", payment);
        return payment;
    }
}
