package com.leovegasgroup.wallet.service.impl;

import com.leovegasgroup.wallet.cache.domain.Account;
import com.leovegasgroup.wallet.cache.repository.AccountRepository;
import com.leovegasgroup.wallet.domain.Player;
import com.leovegasgroup.wallet.domain.Transaction;
import com.leovegasgroup.wallet.domain.enumeration.TransactionType;
import com.leovegasgroup.wallet.repository.TransactionRepository;
import com.leovegasgroup.wallet.rest.error.BadRequestAlertException;
import com.leovegasgroup.wallet.service.TransactionService;
import com.leovegasgroup.wallet.service.dto.TransactionDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * Service Implementation for managing {@link Transaction}.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final String ENTITY_NAME = "Transaction";

    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    Lock lock = new java.util.concurrent.locks.ReentrantLock();

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public Account balance(Long playerId) {
        Account account = accountRepository.get(playerId);
        return account;
    }

    @Override
    public void run(TransactionDTO transactionDTO) throws BadRequestAlertException {
        try {
            lock.lock();
            if (transactionDTO.getType() == TransactionType.Credit) {
                credit(transactionDTO);
            } else {
                // transactionDTO.getType() == TransactionType.Debit
                debit(transactionDTO);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void debit(TransactionDTO transactionDTO) {
        Account account = accountRepository.get(transactionDTO.getPlayerId());
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
            transactionRepository.add(transaction);
            accountRepository.update(account);
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            throw new BadRequestAlertException("A new transaction cannot have a duplicate ID", ENTITY_NAME, "id-duplicate");
        }
    }

    @Override
    public void credit(TransactionDTO transactionDTO) {

        Account account = accountRepository.get(transactionDTO.getPlayerId());
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
            transactionRepository.add(transaction);
            accountRepository.update(account);
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            throw new BadRequestAlertException("A new transaction cannot have a duplicate ID", ENTITY_NAME, "id-duplicate");
        }
    }

    @Override
    public List<Transaction> history(Long playerId) {
        return transactionRepository.findAllByPlayerId(playerId);
    }
}
