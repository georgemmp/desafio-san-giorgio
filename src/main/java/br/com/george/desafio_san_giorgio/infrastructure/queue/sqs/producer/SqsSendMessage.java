package br.com.george.desafio_san_giorgio.infrastructure.queue.sqs.producer;

import br.com.george.desafio_san_giorgio.application.gateway.SendPaymentMessage;
import br.com.george.desafio_san_giorgio.domain.entity.Payment;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqsSendMessage implements SendPaymentMessage {

    private final SqsTemplate sqsTemplate;

    @Value("${aws.queue}")
    private String queue;

    @Override
    public void execute(Payment payment) {
        log.info("Sending message {} to queue {}", payment, queue);
        this.sqsTemplate.send(queue, payment);
        log.info("Message sent {}", payment);
    }
}
