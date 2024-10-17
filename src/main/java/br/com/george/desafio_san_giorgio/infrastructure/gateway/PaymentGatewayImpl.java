package br.com.george.desafio_san_giorgio.infrastructure.gateway;

import br.com.george.desafio_san_giorgio.application.gateway.PaymentGateway;
import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import br.com.george.desafio_san_giorgio.infrastructure.data.repository.PaymentRepository;
import br.com.george.desafio_san_giorgio.infrastructure.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    @Override
    public Payment updatePayment(Payment payment) {
        var entity = this.mapper.toEntity(payment);
        var updated = this.repository.save(entity);
        return this.mapper.toDomain(updated);
    }

    @Override
    public Payment findPaymentByCode(String code) {
        var entity = this.repository.findByCode(code);
        return this.mapper.toDomain(entity);
    }
}
