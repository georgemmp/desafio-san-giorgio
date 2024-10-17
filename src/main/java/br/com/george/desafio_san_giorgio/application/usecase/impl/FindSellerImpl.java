package br.com.george.desafio_san_giorgio.application.usecase.impl;

import br.com.george.desafio_san_giorgio.application.gateway.SellerGateway;
import br.com.george.desafio_san_giorgio.application.usecase.FindSeller;
import br.com.george.desafio_san_giorgio.domain.entity.Seller;
import br.com.george.desafio_san_giorgio.domain.exception.SellerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static br.com.george.desafio_san_giorgio.domain.type.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class FindSellerImpl implements FindSeller {

    private final SellerGateway sellerGateway;

    @Override
    public Seller execute(String code) {
        Seller seller = this.sellerGateway.findSellerByCode(code);
        if (Objects.isNull(seller))
            throw new SellerNotFound(String.format(SELLER_NOT_FOUND.getMessage(), code));
        return seller;
    }
}
