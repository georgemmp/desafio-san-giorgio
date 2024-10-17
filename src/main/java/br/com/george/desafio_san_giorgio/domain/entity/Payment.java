package br.com.george.desafio_san_giorgio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    private String code;
    private BigDecimal value;
    private StatusPayment statusPayment;

    public void setStatusPaymentByValue(BigDecimal value) {
        int compareValues = this.value.compareTo(value);
        switch (compareValues) {
            case -1 -> this.statusPayment = StatusPayment.PARTIAL;
            case 1 -> this.statusPayment = StatusPayment.OVERPAYMENT;
            default -> this.statusPayment = StatusPayment.FULL;
        }
    }
}
