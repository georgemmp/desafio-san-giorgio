package br.com.george.desafio_san_giorgio.domain.entity;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentConfirmation {

    private String sellerCode;
    private List<Payment> payments;
}
