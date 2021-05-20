package com.leovegasgroup.wallet.repository;

import com.leovegasgroup.wallet.service.dto.PlayerDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Player entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlayerRepositoryCustom {
    List<PlayerDTO> playersWithAccountBalance();
}
