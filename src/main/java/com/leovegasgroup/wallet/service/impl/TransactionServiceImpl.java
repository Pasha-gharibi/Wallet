package com.leovegasgroup.wallet.service.impl;

import com.leovegasgroup.wallet.domain.Account;
import com.leovegasgroup.wallet.domain.Player;
import com.leovegasgroup.wallet.domain.Transaction;
import com.leovegasgroup.wallet.domain.enumeration.TransactionType;
import com.leovegasgroup.wallet.repository.AccountRepository;
import com.leovegasgroup.wallet.repository.TransactionRepository;
import com.leovegasgroup.wallet.rest.error.BadRequestAlertException;
import com.leovegasgroup.wallet.service.TransactionService;
import com.leovegasgroup.wallet.service.dto.TransactionDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service Implementation for managing {@link Transaction}.
 */
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private static final String ENTITY_NAME = "Transaction";

    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public void run(TransactionDTO transactionDTO) throws BadRequestAlertException {
        if (transactionDTO.getType() == TransactionType.Credit) {
            credit(transactionDTO);
        } else {
            // transactionDTO.getType() == TransactionType.Debit
            debit(transactionDTO);
        }
    }

    @Override
    public void debit(TransactionDTO transactionDTO) {
        Account account = accountRepository.findFirstByPlayerId(transactionDTO.getPlayerId());
        if (account.getBalance().compareTo(transactionDTO.getAmount()) < 0) {
            throw new BadRequestAlertException("Insufficient found", ENTITY_NAME, "insufficient-found");
        }
        account.setBalance(account.getBalance().subtract(transactionDTO.getAmount()));
        account.setLastModified(ZonedDateTime.now());

        Transaction transaction = new Transaction()
                .id(transactionDTO.getId())
                .amount(transactionDTO.getAmount())
                .balance(account.getBalance())
                .lastModified(account.getLastModified())
                .type(TransactionType.Debit)
                .player(new Player().id(transactionDTO.getPlayerId()));

        try {
            transactionRepository.save(transaction);
            accountRepository.saveWithLock(account);
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            throw new BadRequestAlertException("A new transaction cannot have a duplicate ID", ENTITY_NAME, "id-duplicate");
        }
    }

    @Override
    public void credit(TransactionDTO transactionDTO) {
        Account account = accountRepository.findFirstByPlayerId(transactionDTO.getPlayerId());
        account.setBalance(account.getBalance().add(transactionDTO.getAmount()));
        account.setLastModified(ZonedDateTime.now());
        Transaction transaction = new Transaction()
                .id(transactionDTO.getId())
                .amount(transactionDTO.getAmount())
                .balance(account.getBalance())
                .lastModified(account.getLastModified())
                .type(TransactionType.Credit)
                .player(new Player().id(transactionDTO.getPlayerId()));

        try {
            transactionRepository.save(transaction);
            accountRepository.saveWithLock(account);
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            throw new BadRequestAlertException("A new transaction cannot have a duplicate ID", ENTITY_NAME, "id-duplicate");
        }
    }

    @Override
    public List<Transaction> history(Long playerId) {
        return transactionRepository.findAllByPlayerId(playerId);
    }
}
