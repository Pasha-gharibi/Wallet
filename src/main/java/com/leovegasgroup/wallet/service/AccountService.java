package com.leovegasgroup.wallet.service;

import com.leovegasgroup.wallet.domain.Account;

/**
 * Service Interface for managing {@link com.leovegasgroup.wallet.domain.Account}.
 */
public interface AccountService {

    /**
     * @param playerId
     */
    Account balance(Long playerId);
}
