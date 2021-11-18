package com.kodilla.transfersgateway.validator;

import com.kodilla.transfersgateway.connector.AccountsConnector;
import com.kodilla.transfersgateway.controller.request.TransferRequest;
import com.kodilla.transfersgateway.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferValidator {
    private final AccountsConnector accountsConnector;

    public boolean validate(TransferRequest request) {
        AccountDto accountDto = accountsConnector.getAccount(request.getSenderAccount());
        return accountDto.getAvailableFunds().compareTo(request.getAmount()) >= 0;
    }
}
