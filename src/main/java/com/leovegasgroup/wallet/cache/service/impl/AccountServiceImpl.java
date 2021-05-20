package com.leovegasgroup.wallet.cache.service.impl;

import com.leovegasgroup.wallet.cache.repository.AccountRepository;
import com.leovegasgroup.wallet.cache.domain.Account;
import com.leovegasgroup.wallet.cache.service.AccountService;
import com.leovegasgroup.wallet.service.dto.PlayerDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Account service implementing directly in redis cache
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private void initAccountForPlayer(Long playerId,BigDecimal balance){
        Account account = accountRepository.get(playerId);
        if(account == null) {
            accountRepository.create(new Account(balance, ZonedDateTime.now(),playerId));
        }else{
            accountRepository.update(new Account(balance, ZonedDateTime.now(),playerId));
        }
    }

    public void initAccountForPlayers(List<PlayerDTO> players){
        players.stream().forEach(player -> initAccountForPlayer(player.getId(),player.getBalance()));
    }

}
