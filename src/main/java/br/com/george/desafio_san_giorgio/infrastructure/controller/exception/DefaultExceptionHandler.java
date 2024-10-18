package br.com.george.desafio_san_giorgio.infrastructure.controller.exception;

import br.com.george.desafio_san_giorgio.domain.exception.PaymentNotFound;
import br.com.george.desafio_san_giorgio.domain.exception.SellerNotFound;
import br.com.george.desafio_san_giorgio.infrastructure.controller.response.DefaultExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    private static final Map<Class<? extends Throwable>, HttpStatus> EXCEPTION_STATUS_MAP = new HashMap<>();

    static {
        EXCEPTION_STATUS_MAP.put(SellerNotFound.class, HttpStatus.NOT_FOUND);
        EXCEPTION_STATUS_MAP.put(PaymentNotFound.class, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<DefaultExceptionResponse> customizeException(Throwable throwable, HttpServletRequest request) {
        log.error("An error ocurred {}", throwable.getMessage());
        HttpStatus status = EXCEPTION_STATUS_MAP.getOrDefault(throwable.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        return createResponse(throwable, request, status);
    }

    private ResponseEntity<DefaultExceptionResponse> createResponse(Throwable throwable, HttpServletRequest request, HttpStatus status) {
        DefaultExceptionResponse response = new DefaultExceptionResponse(throwable.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(response);
    }
}
