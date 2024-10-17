package br.com.george.desafio_san_giorgio.application.usecase;

import br.com.george.desafio_san_giorgio.domain.Seller;

public interface GetSeller {

    Seller execute(String code);
}
