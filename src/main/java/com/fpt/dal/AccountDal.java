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
        Account account = em.find(Account.class, username);
        em.close();

        return account != null && BCrypt.checkpw(password, account.getPassword());
    }

    // 2.3. registerAccount() method
    public boolean registerAccount(String username, String rawPassword) {
        EntityManager em = emf.createEntityManager();
        if (em.find(Account.class, username) != null)
            return false;

        em.getTransaction().begin();
        String hashedPass = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        em.persist(new Account(username, hashedPass, "user"));
        em.getTransaction().commit();

        em.close();

        return true;
    }
}
