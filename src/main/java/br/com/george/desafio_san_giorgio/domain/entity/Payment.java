package br.com.george.desafio_san_giorgio.domain.entity;

import br.com.george.desafio_san_giorgio.domain.type.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    private String code;
    private BigDecimal amountPaid;
    private BigDecimal amount;
    private PaymentStatus paymentStatus;
    private Seller seller;

    public void setStatusPaymentByAmount(BigDecimal value) {
        int compareValues = value.compareTo(this.amount);
        switch (compareValues) {
            case -1 -> this.paymentStatus = PaymentStatus.PARTIAL;
            case 1 -> this.paymentStatus = PaymentStatus.OVERPAYMENT;
            case 0 -> this.paymentStatus = PaymentStatus.FULL;
            default -> this.paymentStatus = PaymentStatus.PENDING;
        }
    }
}
