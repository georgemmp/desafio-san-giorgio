package br.com.george.desafio_san_giorgio.domain.entity;

import br.com.george.desafio_san_giorgio.domain.type.StatusPayment;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    private String code;
    @Setter private BigDecimal amountPaid;
    private BigDecimal amount;
    private StatusPayment statusPayment;
    private Seller seller;

    public void setStatusPaymentByAmount(BigDecimal value) {
        int compareValues = this.amount.compareTo(value);
        switch (compareValues) {
            case -1 -> this.statusPayment = StatusPayment.PARTIAL;
            case 1 -> this.statusPayment = StatusPayment.OVERPAYMENT;
            case 0 -> this.statusPayment = StatusPayment.FULL;
            default -> this.statusPayment = StatusPayment.PENDING;
        }
    }
}
