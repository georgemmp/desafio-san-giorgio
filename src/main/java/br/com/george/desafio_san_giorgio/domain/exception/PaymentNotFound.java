package br.com.george.desafio_san_giorgio.domain.exception;

public class PaymentNotFound extends RuntimeException {

    public PaymentNotFound(String message) {
        super(message);
    }
}
