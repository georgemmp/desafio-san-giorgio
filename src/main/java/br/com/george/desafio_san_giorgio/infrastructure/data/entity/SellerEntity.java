package br.com.george.desafio_san_giorgio.infrastructure.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "seller")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class SellerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String code;

    @OneToMany(mappedBy = "seller")
    private List<PaymentEntity> paymentsToReceive;
}
