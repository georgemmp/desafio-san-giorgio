package br.com.george.desafio_san_giorgio.infrastructure.data.repository;

import br.com.george.desafio_san_giorgio.infrastructure.data.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {

    PaymentEntity findByCode(String code);
}
