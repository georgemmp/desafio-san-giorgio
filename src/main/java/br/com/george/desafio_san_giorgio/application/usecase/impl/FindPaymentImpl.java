package br.com.george.desafio_san_giorgio.application.usecase.impl;

import br.com.george.desafio_san_giorgio.application.gateway.ChargeGateway;
import br.com.george.desafio_san_giorgio.application.gateway.SellerGateway;
import br.com.george.desafio_san_giorgio.application.usecase.FindCharge;
import br.com.george.desafio_san_giorgio.application.usecase.FindSeller;
import br.com.george.desafio_san_giorgio.domain.entity.Charge;
import br.com.george.desafio_san_giorgio.domain.entity.Seller;
import br.com.george.desafio_san_giorgio.domain.exception.ChargeNotFound;
import br.com.george.desafio_san_giorgio.domain.exception.SellerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static br.com.george.desafio_san_giorgio.domain.type.ExceptionMessages.CHARGE_NOT_FOUND;
import static br.com.george.desafio_san_giorgio.domain.type.ExceptionMessages.SELLER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FindChargeImpl implements FindCharge {

    private final ChargeGateway chargeGateway;

    @Override
    public Charge execute(String code) {
        Charge charge = this.chargeGateway.findChargeByCode(code);
        if (Objects.isNull(charge))
            throw new ChargeNotFound(String.format(CHARGE_NOT_FOUND.getMessage(), code));
        return charge;
    }
}
