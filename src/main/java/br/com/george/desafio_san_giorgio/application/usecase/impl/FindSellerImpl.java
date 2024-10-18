package br.com.george.desafio_san_giorgio.application.usecase.impl;

import br.com.george.desafio_san_giorgio.application.gateway.SellerGateway;
import br.com.george.desafio_san_giorgio.application.usecase.FindSeller;
import br.com.george.desafio_san_giorgio.domain.entity.Seller;
import br.com.george.desafio_san_giorgio.domain.exception.SellerNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static br.com.george.desafio_san_giorgio.domain.type.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindSellerImpl implements FindSeller {

    private final SellerGateway sellerGateway;

    @Override
    public Seller execute(String code) {
        log.info("Looking for seller {}", code);
        Seller seller = this.sellerGateway.findSellerByCode(code);
        if (Objects.isNull(seller))
            throw new SellerNotFound(String.format(SELLER_NOT_FOUND.getMessage(), code));
        log.info("Seller found {}", seller);
        return seller;
    }
}
