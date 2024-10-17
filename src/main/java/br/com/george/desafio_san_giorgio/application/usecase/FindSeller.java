package br.com.george.desafio_san_giorgio.application.usecase;

import br.com.george.desafio_san_giorgio.domain.entity.Seller;

public interface FindSeller {

    Seller execute(String code);
}
