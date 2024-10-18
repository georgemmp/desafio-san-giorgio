package br.com.george.desafio_san_giorgio.infrastructure.mapper;

import br.com.george.desafio_san_giorgio.domain.entity.PaymentConfirmation;
import br.com.george.desafio_san_giorgio.infrastructure.controller.request.ConfirmationRequest;
import br.com.george.desafio_san_giorgio.infrastructure.controller.response.ConfirmationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentConfirmationMapper {

    @Mapping(target = "payments", source = "paymentItemRequests")
    PaymentConfirmation toDomain(ConfirmationRequest request);

    @Mapping(target = "paymentItemResponses", source = "payments")
    ConfirmationResponse toResponse(PaymentConfirmation paymentConfirmation);


}
