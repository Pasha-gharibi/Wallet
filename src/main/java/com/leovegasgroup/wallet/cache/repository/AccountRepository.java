package com.leovegasgroup.wallet.cache.repository;

import com.leovegasgroup.wallet.cache.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class AccountRepository {
    final Logger logger = LoggerFactory.getLogger(AccountRepository.class);

    private final HashOperations hashOperations;
    private final static String ENTITY_NAME = "ACCOUNT";

    public AccountRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void create(Account account) {
        hashOperations.put(ENTITY_NAME, account.getPlayerId(), account);
        logger.info(String.format("ACCOUNT with playerID %s saved", account.getPlayerId()));
    }

    public Account get(Long playerId) {
        return (Account) hashOperations.get(ENTITY_NAME, playerId);
    }

    public Map<Long, Account> getAll() {
        return hashOperations.entries(ENTITY_NAME);
    }

    public void update(Account account) {
        hashOperations.put(ENTITY_NAME, account.getPlayerId(), account);
        logger.info(String.format("ACCOUNT with playerID %d updated", account.getPlayerId()));
    }

    public void delete(Long playerId) {
        hashOperations.delete(ENTITY_NAME, playerId);
        logger.info(String.format("ACCOUNT with ID %d deleted", playerId));
    }
}
