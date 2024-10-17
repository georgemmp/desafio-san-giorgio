package br.com.george.desafio_san_giorgio.domain.exception;

public class SellerNotFound extends RuntimeException {

    public SellerNotFound(String message) {
        super(message);
    }
}
