package br.com.george.desafio_san_giorgio.application.usecase;

import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.domain.entity.PaymentConfirmation;

import java.util.List;

public interface ConfirmPayments {

    void execute(PaymentConfirmation paymentConfirmation);
}
