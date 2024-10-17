package br.com.george.desafio_san_giorgio.application.usecase;

import br.com.george.desafio_san_giorgio.domain.entity.Charge;

public interface FindCharge {

    Charge execute(String code);
}
