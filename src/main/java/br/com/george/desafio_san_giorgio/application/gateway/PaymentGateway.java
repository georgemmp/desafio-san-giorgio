package br.com.george.desafio_san_giorgio.application.gateway;

import br.com.george.desafio_san_giorgio.domain.entity.Payment;

public interface PaymentGateway {

    Payment updatePayment(Payment payment);
    Payment findPaymentByCode(String code);
}
