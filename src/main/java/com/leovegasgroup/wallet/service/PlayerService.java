package com.leovegasgroup.wallet.service;

import com.leovegasgroup.wallet.domain.Player;

import java.util.List;

/**
 * Service Interface for managing {@link com.leovegasgroup.wallet.domain.Player}.
 */
public interface PlayerService {

    /**
     * Get all the players.
     *
     * @return the list of entities.
     */

    List<Player> findAll();

    List findAllWithBalance();


}
