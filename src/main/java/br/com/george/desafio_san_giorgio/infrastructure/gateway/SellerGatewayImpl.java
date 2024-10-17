package br.com.george.desafio_san_giorgio.infrastructure.gateway;

import br.com.george.desafio_san_giorgio.application.gateway.SellerGateway;
import br.com.george.desafio_san_giorgio.domain.entity.Seller;
import br.com.george.desafio_san_giorgio.infrastructure.data.repository.SellerRepository;
import br.com.george.desafio_san_giorgio.infrastructure.mapper.SellerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SellerGatewayImpl implements SellerGateway {

    private final SellerRepository repository;
    private final SellerMapper mapper;

    @Override
    public Seller findSellerByCode(String code) {
        var entity = this.repository.findByCode(code);
        if (Objects.isNull(entity)) return null;
        return this.mapper.toDomain(entity);
    }
}
