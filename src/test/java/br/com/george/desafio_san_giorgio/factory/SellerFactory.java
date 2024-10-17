package br.com.george.desafio_san_giorgio.factory;

import br.com.george.desafio_san_giorgio.domain.entity.Seller;

public class SellerFactory {

    private SellerFactory() {}

    public static Seller create(String code) {
        return Seller.builder()
                .code(code)
                .build();
    }
}
