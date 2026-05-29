package com.fpt.dal;

import com.fpt.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.mindrot.jbcrypt.BCrypt;

public class AccountDal {
    // 2.1. EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserAuthPU");

    // 2.2. checkLogin() method
    public boolean checkLogin(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            Account account = em.createQuery("SELECT a FROM Account a WHERE a.username = :user", Account.class)
                    .setParameter("user", username)
                    .getSingleResult();
            return BCrypt.checkpw(password, account.getPassword());
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    // 2.3. registerAccount() method
    public boolean registerAccount(String username, String rawPassword) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(a) FROM Account a WHERE a.username = :user", Long.class)
                    .setParameter("user", username)
                    .getSingleResult();
            
            if (count > 0) return false;

            em.getTransaction().begin();
            String hashedPass = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
            em.persist(new Account(username, hashedPass, "user"));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
}
