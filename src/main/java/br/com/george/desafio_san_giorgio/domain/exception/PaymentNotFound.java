package br.com.george.desafio_san_giorgio.domain.exception;

public class ChargeNotFound extends RuntimeException {

    public ChargeNotFound(String message) {
        super(message);
    }
}
