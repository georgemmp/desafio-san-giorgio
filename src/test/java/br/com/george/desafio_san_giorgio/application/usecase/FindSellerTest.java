package br.com.george.desafio_san_giorgio.application.usecase;

import br.com.george.desafio_san_giorgio.application.gateway.SellerGateway;
import br.com.george.desafio_san_giorgio.application.usecase.impl.FindSellerImpl;
import br.com.george.desafio_san_giorgio.domain.entity.Seller;
import br.com.george.desafio_san_giorgio.domain.exception.SellerNotFound;
import br.com.george.desafio_san_giorgio.factory.SellerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindSellerTest {

    @InjectMocks
    private FindSellerImpl findSeller;

    @Mock
    private SellerGateway sellerGateway;

    private static final UUID CODE = UUID.randomUUID();

    @Test
    void shouldFindSellerByCode() {
        Seller seller = SellerFactory.create(CODE.toString());

        when(this.sellerGateway.findSellerByCode(anyString())).thenReturn(seller);

        var result = this.findSeller.execute(CODE.toString());

        assertEquals(CODE.toString(), result.getCode());
    }

    @Test
    void shouldThrowNotFoundException() {
        when(this.sellerGateway.findSellerByCode(anyString())).thenReturn(null);

        assertThrows(SellerNotFound.class, () -> this.findSeller.execute(CODE.toString()));
    }
}
