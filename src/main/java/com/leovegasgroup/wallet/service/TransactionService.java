package com.leovegasgroup.wallet.service;

import com.leovegasgroup.wallet.domain.Transaction;
import com.leovegasgroup.wallet.service.dto.TransactionDTO;

import java.util.List;

/**
 * Service Interface for managing {@link com.leovegasgroup.wallet.domain.Transaction}.
 */
public interface TransactionService {

    void debit(TransactionDTO transactionDTO);

    void credit(TransactionDTO transactionDTO);

    List<Transaction> history(Long playerId);

    void run(TransactionDTO transactionDTO);
}
