package br.com.george.desafio_san_giorgio.infrastructure.controller;

public record DefaultExceptionResponse(
        String message,
        String path
) {
}
