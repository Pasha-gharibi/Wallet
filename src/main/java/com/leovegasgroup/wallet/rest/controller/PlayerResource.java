package com.leovegasgroup.wallet.rest.controller;

import com.leovegasgroup.wallet.domain.Player;
import com.leovegasgroup.wallet.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing {@link com.leovegasgroup.wallet.domain.Player}.
 */
@RestController
@RequestMapping("/api")
public class PlayerResource {

    private static final String ENTITY_NAME = "walletPlayer";
    private final Logger log = LoggerFactory.getLogger(PlayerResource.class);
    private final PlayerService playerService;


    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the List<player>.
     */
    @GetMapping("/players")
    public ResponseEntity<List<Player>> getPlayers() {
        log.debug("REST request to get all Players");
        List<Player> players = playerService.findAll();
        return ResponseEntity.ok(players);
    }

}
