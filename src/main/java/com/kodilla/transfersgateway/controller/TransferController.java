package com.kodilla.transfersgateway.controller;

import com.kodilla.commons.Transfer;
import com.kodilla.transfersgateway.controller.request.TransferRequest;
import com.kodilla.transfersgateway.service.TransferProducer;
import com.kodilla.transfersgateway.validator.TransferValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferProducer transferProducer;
    private final TransferValidator validator;

    @PostMapping
    public void saveTransfer(@RequestBody TransferRequest request) {
        log.info("Recieved transfer request: {}", request);

        if(validator.validate(request)) {
            Transfer transfer = new Transfer();
            transfer.setAmount(request.getAmount());
            transfer.setRecipientAccount(request.getRecipientAccount());
            transfer.setSenderAccount(request.getSenderAccount());
            transfer.setTitle(request.getTitle());

            transferProducer.sendTransfer(transfer);
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "You have not enough funds on your account.");
        }
    }
}
