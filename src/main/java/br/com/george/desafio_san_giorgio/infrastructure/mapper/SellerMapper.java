package br.com.george.desafio_san_giorgio.infrastructure.mapper;

import br.com.george.desafio_san_giorgio.domain.entity.Seller;
import br.com.george.desafio_san_giorgio.infrastructure.data.entity.SellerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SellerMapper {

//    @Mapping(target = "paymentsToReceive", ignore = true)
    Seller toDomain(SellerEntity sellerEntity);
}
