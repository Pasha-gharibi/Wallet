package com.leovegasgroup.wallet.repository.impl;

import com.leovegasgroup.wallet.domain.Account;
import com.leovegasgroup.wallet.repository.AccountRepositoryCustom;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceUnit;

public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void saveWithLock(Account account) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        if (!ObjectUtils.isEmpty(account) && !entityManager.contains(account)) {
            entityManager.lock(account, LockModeType.PESSIMISTIC_WRITE);
            entityManager.persist(account);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }



}
