package com.leovegasgroup.wallet.service.impl;

import com.leovegasgroup.wallet.domain.Account;
import com.leovegasgroup.wallet.repository.AccountRepository;
import com.leovegasgroup.wallet.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Account}.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account balance(Long playerId) {
        Account account = accountRepository.findFirstByPlayerId(playerId);
        return account;
    }

}
