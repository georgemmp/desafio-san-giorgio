package br.com.george.desafio_san_giorgio.application.usecase;

import br.com.george.desafio_san_giorgio.application.gateway.PaymentGateway;
import br.com.george.desafio_san_giorgio.application.usecase.impl.ConfirmPaymentsImpl;
import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.domain.type.StatusPayment;
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
        var originalPayment = PaymentFactory.create(PAYMENT_VALUE, PAYMENT_CODE);
        var confirmation = PaymentConfirmationFactory.create(originalPayment);

        when(this.findPayment.execute(PAYMENT_CODE)).thenReturn(fullPayment);
        when(this.paymentGateway.updatePayment(any(Payment.class))).thenReturn(fullPayment);

        this.confirmPayments.execute(confirmation);
        assertEquals(StatusPayment.FULL, fullPayment.getStatusPayment());
    }

    @Test
    void confirmPartialPayment() {
        var partialPayment = PaymentFactory.create(BigDecimal.valueOf(50), PAYMENT_CODE);
        var originalPayment = PaymentFactory.create(PAYMENT_VALUE, PAYMENT_CODE);
        var confirmation = PaymentConfirmationFactory.create(originalPayment);

        when(this.findPayment.execute(PAYMENT_CODE)).thenReturn(partialPayment);
        when(this.paymentGateway.updatePayment(any(Payment.class))).thenReturn(partialPayment);

        this.confirmPayments.execute(confirmation);
        assertEquals(StatusPayment.PARTIAL, partialPayment.getStatusPayment());
    }

    @Test
    void confirmOverpayment() {
        var overpayment = PaymentFactory.create(BigDecimal.valueOf(150), PAYMENT_CODE);
        var origianalPayment = PaymentFactory.create(PAYMENT_VALUE, PAYMENT_CODE);
        var confirmation = PaymentConfirmationFactory.create(origianalPayment);

        when(this.findPayment.execute(PAYMENT_CODE)).thenReturn(overpayment);
        when(this.paymentGateway.updatePayment(any(Payment.class))).thenReturn(overpayment);

        this.confirmPayments.execute(confirmation);
        assertEquals(StatusPayment.OVERPAYMENT, overpayment.getStatusPayment());
    }
}
