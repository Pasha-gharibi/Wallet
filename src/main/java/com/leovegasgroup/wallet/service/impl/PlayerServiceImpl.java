package com.leovegasgroup.wallet.service.impl;

import com.leovegasgroup.wallet.domain.Player;
import com.leovegasgroup.wallet.repository.PlayerRepository;
import com.leovegasgroup.wallet.service.PlayerService;
import com.leovegasgroup.wallet.service.dto.PlayerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing {@link Player}.
 */
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> findAll() {
        log.debug("Request to get all Players");
        return playerRepository.findAll();
    }

    @Override
    public List<PlayerDTO> findAllWithBalance() {
        return playerRepository.playersWithAccountBalance();
    }
}
