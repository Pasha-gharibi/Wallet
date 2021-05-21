package com.leovegasgroup.wallet.repository;

import com.leovegasgroup.wallet.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Account entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account>, AccountRepositoryCustom {
    Account findFirstByPlayerId(Long playerId);
}
