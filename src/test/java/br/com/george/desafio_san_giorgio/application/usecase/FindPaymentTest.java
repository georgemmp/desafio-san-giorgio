package br.com.george.desafio_san_giorgio.application.usecase;

import br.com.george.desafio_san_giorgio.application.gateway.PaymentGateway;
import br.com.george.desafio_san_giorgio.application.usecase.impl.FindPaymentImpl;
import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.domain.entity.Seller;
import br.com.george.desafio_san_giorgio.domain.exception.PaymentNotFound;
import br.com.george.desafio_san_giorgio.domain.exception.SellerNotFound;
import br.com.george.desafio_san_giorgio.factory.PaymentFactory;
import br.com.george.desafio_san_giorgio.factory.SellerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPaymentTest {

    @InjectMocks
    private FindPaymentImpl findPayment;

    @Mock
    private PaymentGateway paymentGateway;

    private static final UUID CODE = UUID.randomUUID();

    @Test
    void shouldFindSellerByCode() {
        Payment payment = PaymentFactory.create(CODE.toString());

        when(this.paymentGateway.findPaymentByCode(anyString())).thenReturn(payment);

        var result = this.findPayment.execute(CODE.toString());

        assertEquals(CODE.toString(), result.getCode());
    }

    @Test
    void shouldThrowNotFoundException() {
        when(this.paymentGateway.findPaymentByCode(anyString())).thenReturn(null);

        assertThrows(PaymentNotFound.class, () -> this.findPayment.execute(CODE.toString()));
    }
}
