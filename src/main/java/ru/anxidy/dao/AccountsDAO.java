package ru.anxidy.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.anxidy.entities.Account;

@Repository
public class AccountsDAO {
    private final EntityManager manager;

    @Autowired
    public AccountsDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void create(Account account) {
        manager.persist(account);
    }

    public Account findById(int id) {
        return manager.find(Account.class, id);
    }

    public Account findByLogin(String login) {
        return manager.createQuery("from Account where login =:l", Account.class)
                .setParameter("l", login)
                .getSingleResult();
    }

    public Account findByLoginAndPassword(String login, String password) {
        return manager.createQuery("from Account where login =:l and password =:p", Account.class)
                .setParameter("l", login)
                .setParameter("p", password)
                .getSingleResult();
    }
}