package br.com.george.desafio_san_giorgio.application.usecase;

import br.com.george.desafio_san_giorgio.domain.entity.Payment;

public interface FindPayment {

    Payment execute(String code);
}
