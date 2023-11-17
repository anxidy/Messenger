package ru.anxidy.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.anxidy.entities.Account;

@Repository
public class AccountsDAO {

    @PersistenceContext
    private EntityManager manager;

    public AccountsDAO() {}
    public AccountsDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
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