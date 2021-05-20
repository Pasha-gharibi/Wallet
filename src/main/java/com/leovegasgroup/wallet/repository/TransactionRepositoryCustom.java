package com.leovegasgroup.wallet.repository;

import com.leovegasgroup.wallet.domain.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Transaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionRepositoryCustom {
    void add(Transaction transaction) throws ConstraintViolationException;
}

