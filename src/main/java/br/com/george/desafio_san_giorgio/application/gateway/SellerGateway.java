package br.com.george.desafio_san_giorgio.application.gateway;

import br.com.george.desafio_san_giorgio.domain.entity.Seller;

public interface SellerGateway {

    Seller findSellerByCode(String code);
}
