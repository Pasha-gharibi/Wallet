package com.leovegasgroup.wallet.repository.impl;

import com.leovegasgroup.wallet.repository.PlayerRepositoryCustom;
import com.leovegasgroup.wallet.service.dto.PlayerDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepositoryCustom {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<PlayerDTO> playersWithAccountBalance() {
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String query = "Select p.id, nvl(sum(t.amount),0) from t_player p left join t_transaction t on p.id = t.player_id group by p.id ";
        List<Object[]> players = entityManager.createNativeQuery(query).getResultList();
        for (Object[] record : players) {
            Long id = Long.valueOf(record[0].toString());
            BigDecimal balance = new BigDecimal(record[1].toString());
            playerDTOS.add(new PlayerDTO(id,balance));
        };

        entityManager.getTransaction().commit();
        return playerDTOS;
    }
}
