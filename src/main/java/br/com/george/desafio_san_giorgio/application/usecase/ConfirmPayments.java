package br.com.george.desafio_san_giorgio.application.usecase;

import br.com.george.desafio_san_giorgio.domain.entity.PaymentConfirmation;

public interface ConfirmPayments {

    PaymentConfirmation execute(PaymentConfirmation paymentConfirmation);
}
