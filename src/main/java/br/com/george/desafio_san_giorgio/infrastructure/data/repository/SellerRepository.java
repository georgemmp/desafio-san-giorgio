package br.com.george.desafio_san_giorgio.infrastructure.data.repository;

import br.com.george.desafio_san_giorgio.infrastructure.data.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerEntity, String> {

    SellerEntity findByCode(String code);
}
