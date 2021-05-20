package com.leovegasgroup.wallet.rest.controller;

import com.leovegasgroup.wallet.config.Dashboard;
import com.leovegasgroup.wallet.domain.Player;
import com.leovegasgroup.wallet.rest.error.BadRequestAlertException;
import com.leovegasgroup.wallet.rest.util.HeaderUtil;
import com.leovegasgroup.wallet.rest.util.ResponseUtil;
import com.leovegasgroup.wallet.service.PlayerService;
import com.leovegasgroup.wallet.service.dto.PlayerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
        return  ResponseEntity.ok(players);
    }


//    /**
//     * {@code POST  /players} : Create a new player.
//     *
//     * @param playerDTO the playerDTO to create.
//     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new playerDTO, or with status {@code 400 (Bad Request)} if the player has already an ID.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PostMapping("/players")
//    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) throws URISyntaxException {
//        log.debug("REST request to save Player : {}", playerDTO);
//        if (playerDTO.getId() != null) {
//            throw new BadRequestAlertException("A new player cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        PlayerDTO result = playerService.save(playerDTO);
//        return ResponseEntity
//                .created(new URI("/api/players/" + result.getId()))
//                .headers(HeaderUtil.createEntityCreationAlert(Dashboard.applicationName, true, ENTITY_NAME, result.getId().toString()))
//                .body(result);
//    }

//    /**
//     * {@code PUT  /players} : Updates an existing player.
//     *
//     * @param playerDTO the playerDTO to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated playerDTO,
//     * or with status {@code 400 (Bad Request)} if the playerDTO is not valid,
//     * or with status {@code 500 (Internal Server Error)} if the playerDTO couldn't be updated.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PutMapping("/players")
//    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO playerDTO) throws URISyntaxException {
//        log.debug("REST request to update Player : {}", playerDTO);
//        if (playerDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        PlayerDTO result = playerService.save(playerDTO);
//        return ResponseEntity
//                .ok()
//                .headers(HeaderUtil.createEntityUpdateAlert(Dashboard.applicationName, true, ENTITY_NAME, playerDTO.getId().toString()))
//                .body(result);
//    }

//    /**
//     * {@code PATCH  /players} : Updates given fields of an existing player.
//     *
//     * @param playerDTO the playerDTO to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated playerDTO,
//     * or with status {@code 400 (Bad Request)} if the playerDTO is not valid,
//     * or with status {@code 404 (Not Found)} if the playerDTO is not found,
//     * or with status {@code 500 (Internal Server Error)} if the playerDTO couldn't be updated.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PatchMapping(value = "/players", consumes = "application/merge-patch+json")
//    public ResponseEntity<PlayerDTO> partialUpdatePlayer(@RequestBody PlayerDTO playerDTO) throws URISyntaxException {
//        log.debug("REST request to update Player partially : {}", playerDTO);
//        if (playerDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//
//        Optional<PlayerDTO> result = playerService.partialUpdate(playerDTO);
//
//        return ResponseUtil.wrapOrNotFound(
//                result,
//                HeaderUtil.createEntityUpdateAlert(Dashboard.applicationName, true, ENTITY_NAME, playerDTO.getId().toString())
//        );
//    }


//    /**
//     * {@code GET  /players/:id} : get the "id" player.
//     *
//     * @param id the id of the playerDTO to retrieve.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the playerDTO, or with status {@code 404 (Not Found)}.
//     */
//    @GetMapping("/players/{id}")
//    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable Long id) {
//        log.debug("REST request to get Player : {}", id);
//        Optional<PlayerDTO> playerDTO = playerService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(playerDTO);
//    }



//    /**
//     * {@code DELETE  /players/:id} : delete the "id" player.
//     *
//     * @param id the id of the playerDTO to delete.
//     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
//     */
//    @DeleteMapping("/players/{id}")
//    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
//        log.debug("REST request to delete Player : {}", id);
//        playerService.delete(id);
//        return ResponseEntity
//                .noContent()
//                .headers(HeaderUtil.createEntityDeletionAlert(Dashboard.applicationName, true, ENTITY_NAME, id.toString()))
//                .build();
//    }
}
