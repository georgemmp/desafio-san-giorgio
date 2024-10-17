package br.com.george.desafio_san_giorgio.infrastructure.mapper;

import br.com.george.desafio_san_giorgio.domain.entity.Seller;
import br.com.george.desafio_san_giorgio.infrastructure.data.entity.SellerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerMapper {

    Seller toDomain(SellerEntity sellerEntity);
}
