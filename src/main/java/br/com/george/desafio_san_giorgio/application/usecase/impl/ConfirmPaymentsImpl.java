package br.com.george.desafio_san_giorgio.application.usecase.impl;

import br.com.george.desafio_san_giorgio.application.gateway.PaymentGateway;
import br.com.george.desafio_san_giorgio.application.gateway.SendPaymentMessage;
import br.com.george.desafio_san_giorgio.application.usecase.ConfirmPayments;
import br.com.george.desafio_san_giorgio.application.usecase.FindPayment;
import br.com.george.desafio_san_giorgio.application.usecase.FindSeller;
import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.domain.entity.PaymentConfirmation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmPaymentsImpl implements ConfirmPayments {

    private final FindPayment findPayment;
    private final FindSeller findSeller;
    private final PaymentGateway paymentGateway;
    private final SendPaymentMessage sendPaymentMessage;

    @Override
    @Transactional
    public PaymentConfirmation execute(PaymentConfirmation paymentConfirmation) {
        log.info("Processing payment {}", paymentConfirmation);
        this.findSeller.execute(paymentConfirmation.getSellerCode());

        var payments = paymentConfirmation.getPayments();

        var updatedPayments = payments.stream().map(this.confirmPayment()).toList();

        var confirmation = PaymentConfirmation.builder()
                .sellerCode(paymentConfirmation.getSellerCode())
                .payments(updatedPayments)
                .build();

        log.info("Payment processed {}", confirmation);
        return confirmation;
    }

    private Function<Payment, Payment> confirmPayment() {
        return item -> {
            var payment = this.findPayment.execute(item.getCode());
            payment.setStatusPaymentByAmount(item.getAmount());
            payment.setAmountPaid(item.getAmount());
            this.sendPaymentMessage.execute(payment);
            return this.paymentGateway.updatePayment(payment);
        };
    }
}
