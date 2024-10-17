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
    private BigDecimal value;
    private StatusPayment statusPayment;
    private Seller seller;

    public void setStatusPaymentByValue(BigDecimal value) {
        int compareValues = this.value.compareTo(value);
        switch (compareValues) {
            case -1 -> this.statusPayment = StatusPayment.PARTIAL;
            case 1 -> this.statusPayment = StatusPayment.OVERPAYMENT;
            default -> this.statusPayment = StatusPayment.FULL;
        }
    }
}
