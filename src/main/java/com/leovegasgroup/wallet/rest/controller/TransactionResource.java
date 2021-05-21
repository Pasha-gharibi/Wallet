package com.leovegasgroup.wallet.rest.controller;

import com.leovegasgroup.wallet.config.Dashboard;
import com.leovegasgroup.wallet.domain.Transaction;
import com.leovegasgroup.wallet.rest.error.BadRequestAlertException;
import com.leovegasgroup.wallet.rest.util.HeaderUtil;
import com.leovegasgroup.wallet.service.TransactionService;
import com.leovegasgroup.wallet.service.dto.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing {@link com.leovegasgroup.wallet.domain.Transaction}.
 */
@RestController
@RequestMapping("/api")
public class TransactionResource {

    private static final String ENTITY_NAME = "Transaction";
    private final Logger log = LoggerFactory.getLogger(TransactionResource.class);
    private final TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * {@code POST  /transactions} : Create a new transaction.
     *
     * @param transactionDTO the transactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transactionDTO, or with status {@code 400 (Bad Request)} if the transaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transactions")
    public ResponseEntity createTransaction(@RequestBody TransactionDTO transactionDTO) throws URISyntaxException {
        log.debug("REST request to save Transaction : {}", transactionDTO);
        if (transactionDTO.getId() == null) {
            BadRequestAlertException exception = new BadRequestAlertException("A new transaction cannot have a null ID", ENTITY_NAME, "id-null");
            return new ResponseEntity(exception.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            transactionService.run(transactionDTO);
            return ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityCreationAlert(Dashboard.applicationName, true, ENTITY_NAME, transactionDTO.getId().toString()))
                    .build();
        } catch (BadRequestAlertException ex) {
            return new ResponseEntity(ex.toString(),
                    HeaderUtil.createEntityCreationAlert(Dashboard.applicationName, true, ENTITY_NAME, transactionDTO.getId().toString()), HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * {@code GET  /transactions/history/:playerId} : get the "playerId" player.
     *
     * @param playerId the id of the user to retrieve it's history.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Account or with a empty body.
     */
    @GetMapping("/transactions/history/{playerId}")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable Long playerId) {
        log.debug("REST request to get History for Player : {}", playerId);
        List<Transaction> transactions = transactionService.history(playerId);
        return ResponseEntity.ok(transactions);
    }

}
