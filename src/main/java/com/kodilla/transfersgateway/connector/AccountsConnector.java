package com.kodilla.transfersgateway.connector;

import com.kodilla.transfersgateway.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "accounts")
public interface AccountsConnector {

    @GetMapping("/v1/accounts/{nrb}")
    AccountDto getAccount(@PathVariable("nrb") String nrb);
}
