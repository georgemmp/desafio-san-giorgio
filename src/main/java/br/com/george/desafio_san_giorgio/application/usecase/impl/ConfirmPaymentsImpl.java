package br.com.george.desafio_san_giorgio.application.usecase.impl;

import br.com.george.desafio_san_giorgio.application.gateway.PaymentGateway;
import br.com.george.desafio_san_giorgio.application.usecase.ConfirmPayments;
import br.com.george.desafio_san_giorgio.application.usecase.FindPayment;
import br.com.george.desafio_san_giorgio.application.usecase.FindSeller;
import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.domain.entity.PaymentConfirmation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class ConfirmPaymentsImpl implements ConfirmPayments {

    private final FindPayment findPayment;
    private final FindSeller findSeller;
    private final PaymentGateway paymentGateway;

    @Override
    @Transactional
    public void execute(PaymentConfirmation paymentConfirmation) {
        this.findSeller.execute(paymentConfirmation.getSellerCode());

        var payments = paymentConfirmation.getPayments();

        payments.parallelStream().forEach(this.confirmPayment());
    }

    private Consumer<? super Payment> confirmPayment() {
        return item -> {
            var payment = this.findPayment.execute(item.getCode());
            payment.setStatusPaymentByAmount(item.getAmount());
            payment.setAmountPaid(item.getAmount());
            this.paymentGateway.updatePayment(payment);
        };
    }
}
