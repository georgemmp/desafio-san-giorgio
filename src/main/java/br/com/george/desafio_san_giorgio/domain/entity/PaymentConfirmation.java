package br.com.george.desafio_san_giorgio.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentConfirmation {

    private String sellerCode;
    private List<Payment> payments;
}
