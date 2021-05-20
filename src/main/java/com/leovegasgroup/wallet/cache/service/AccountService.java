package com.leovegasgroup.wallet.cache.service;

import com.leovegasgroup.wallet.service.dto.PlayerDTO;

import java.util.List;

public interface AccountService {
    void initAccountForPlayers(List<PlayerDTO> players);
}
