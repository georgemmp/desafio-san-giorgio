package br.com.george.desafio_san_giorgio.infrastructure.controller;

import br.com.george.desafio_san_giorgio.application.usecase.ConfirmPayments;
import br.com.george.desafio_san_giorgio.infrastructure.controller.request.ConfirmationRequest;
import br.com.george.desafio_san_giorgio.infrastructure.controller.response.ConfirmationResponse;
import br.com.george.desafio_san_giorgio.infrastructure.mapper.PaymentConfirmationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentConfirmationMapper mapper;
    private final ConfirmPayments confirmPayments;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ConfirmationResponse confirmPaymet(@RequestBody ConfirmationRequest request) {
        var paymentConfirmation = this.mapper.toDomain(request);
        var result = this.confirmPayments.execute(paymentConfirmation);
        return this.mapper.toResponse(result);
    }
}
