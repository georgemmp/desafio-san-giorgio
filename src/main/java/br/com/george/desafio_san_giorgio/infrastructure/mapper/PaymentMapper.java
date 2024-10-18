package br.com.george.desafio_san_giorgio.infrastructure.mapper;

import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.infrastructure.data.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toDomain(PaymentEntity paymentEntity);
    PaymentEntity toEntity(Payment payment);
}
