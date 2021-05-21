package com.leovegasgroup.wallet.repository;

import com.leovegasgroup.wallet.domain.Account;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Account entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountRepositoryCustom {
    void saveWithLock(Account account);
}
