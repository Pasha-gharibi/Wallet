package com.leovegasgroup.wallet.repository.impl;

import com.leovegasgroup.wallet.domain.Transaction;
import com.leovegasgroup.wallet.repository.TransactionRepositoryCustom;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceUnit;

public class TransactionRepositoryImpl implements TransactionRepositoryCustom {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

//    public void save(Transaction transaction) throws ConstraintViolationException {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        if (!ObjectUtils.isEmpty(transaction) && !entityManager.contains(transaction)) {
//            entityManager.persist(transaction);
//        }
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }

}
