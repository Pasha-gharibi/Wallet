package com.leovegasgroup.wallet.rest.controller;

import com.leovegasgroup.wallet.domain.Account;
import com.leovegasgroup.wallet.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.leovegasgroup.wallet.domain.Account}.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final AccountService accountService;


    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * {@code GET  /transactions/balance/:playerId} : get the "playerId" player.
     *
     * @param playerId the id of the user to retrieve it's account balance.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Account or with a empty body.
     */
    @GetMapping("/Account/balance/{playerId}")
    public ResponseEntity<Account> getBalance(@PathVariable Long playerId) {
        log.debug("REST request to get Balance for Player : {}", playerId);
        Account account = accountService.balance(playerId);
        return ResponseEntity.ok(account);
    }

}
