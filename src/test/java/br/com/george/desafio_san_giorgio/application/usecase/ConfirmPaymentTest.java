package br.com.george.desafio_san_giorgio.application.usecase;

import br.com.george.desafio_san_giorgio.application.gateway.PaymentGateway;
import br.com.george.desafio_san_giorgio.application.gateway.SendPaymentMessage;
import br.com.george.desafio_san_giorgio.application.usecase.impl.ConfirmPaymentsImpl;
import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.domain.type.PaymentStatus;
import br.com.george.desafio_san_giorgio.factory.PaymentConfirmationFactory;
import br.com.george.desafio_san_giorgio.factory.PaymentFactory;
import br.com.george.desafio_san_giorgio.factory.SellerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfirmPaymentTest {

    @Mock
    private FindPayment findPayment;

    @Mock
    private FindSeller findSeller;

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private SendPaymentMessage sendPaymentMessage;

    @InjectMocks
    private ConfirmPaymentsImpl confirmPayments;

    private static final String SELLER_CODE = UUID.randomUUID().toString();
    private static final String PAYMENT_CODE = UUID.randomUUID().toString();
    private static final BigDecimal PAYMENT_VALUE = BigDecimal.valueOf(100);

    @BeforeEach
    void setup() {
        var seller = SellerFactory.create(SELLER_CODE);
        when(this.findSeller.execute(anyString())).thenReturn(seller);
    }

    @Test
    void confirmFullPayment() {
        var fullPayment = PaymentFactory.create(PAYMENT_VALUE, PAYMENT_CODE);
        var defaultPayment = PaymentFactory.create(PAYMENT_VALUE, PAYMENT_CODE);
        defaultPayment.setPaymentStatus(PaymentStatus.FULL);
        var confirmation = PaymentConfirmationFactory.create(fullPayment);

        when(this.findPayment.execute(PAYMENT_CODE)).thenReturn(defaultPayment);
        doNothing().when(this.sendPaymentMessage).execute(defaultPayment);
        when(this.paymentGateway.updatePayment(any(Payment.class))).thenReturn(defaultPayment);

        var result = this.confirmPayments.execute(confirmation);
        result.getPayments().forEach(item -> assertEquals(PaymentStatus.FULL, item.getPaymentStatus()));
    }

    @Test
    void confirmPartialPayment() {
        var partial = PaymentFactory.create(BigDecimal.valueOf(50), PAYMENT_CODE);
        var defaultPayment = PaymentFactory.create(PAYMENT_VALUE, PAYMENT_CODE);
        defaultPayment.setPaymentStatus(PaymentStatus.PARTIAL);
        var confirmation = PaymentConfirmationFactory.create(partial);

        when(this.findPayment.execute(PAYMENT_CODE)).thenReturn(defaultPayment);
        doNothing().when(this.sendPaymentMessage).execute(defaultPayment);
        when(this.paymentGateway.updatePayment(any(Payment.class))).thenReturn(defaultPayment);

        var result = this.confirmPayments.execute(confirmation);
        result.getPayments().forEach(item -> assertEquals(PaymentStatus.PARTIAL, item.getPaymentStatus()));
    }

    @Test
    void confirmOverpayment() {
        var overpayment = PaymentFactory.create(BigDecimal.valueOf(150), PAYMENT_CODE);
        var defaultPayment = PaymentFactory.create(PAYMENT_VALUE, PAYMENT_CODE);
        defaultPayment.setPaymentStatus(PaymentStatus.OVERPAYMENT);
        var confirmation = PaymentConfirmationFactory.create(overpayment);

        when(this.findPayment.execute(PAYMENT_CODE)).thenReturn(defaultPayment);
        doNothing().when(this.sendPaymentMessage).execute(defaultPayment);
        when(this.paymentGateway.updatePayment(any(Payment.class))).thenReturn(defaultPayment);

        var result = this.confirmPayments.execute(confirmation);
        result.getPayments().forEach(item -> assertEquals(PaymentStatus.OVERPAYMENT, item.getPaymentStatus()));
    }
}
