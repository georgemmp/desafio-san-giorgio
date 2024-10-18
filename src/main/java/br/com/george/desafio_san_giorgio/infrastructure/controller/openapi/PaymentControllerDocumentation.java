package br.com.george.desafio_san_giorgio.infrastructure.controller.openapi;

import br.com.george.desafio_san_giorgio.infrastructure.controller.request.ConfirmationRequest;
import br.com.george.desafio_san_giorgio.infrastructure.controller.response.ConfirmationResponse;
import br.com.george.desafio_san_giorgio.infrastructure.controller.response.DefaultExceptionResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentControllerDocumentation {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ConfirmationResponse.class)),
                    }),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DefaultExceptionResponse.class)),
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal error", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DefaultExceptionResponse.class)),
                    })
            }
    )
    ConfirmationResponse confirmPaymet(@RequestBody ConfirmationRequest request);
}
