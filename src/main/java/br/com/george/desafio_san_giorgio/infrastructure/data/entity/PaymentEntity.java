package br.com.george.desafio_san_giorgio.infrastructure.data.entity;

import br.com.george.desafio_san_giorgio.domain.type.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "payment")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PaymentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String code;

    @Column(nullable = false, name = "amount_paid")
    private BigDecimal amountPaid;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "status_payment")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "saller_code", nullable = false)
    private SellerEntity seller;
}
