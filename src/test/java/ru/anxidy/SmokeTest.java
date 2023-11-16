package ru.anxidy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.anxidy.entities.Account;

public class SmokeTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
    }

    @After
    public void cleanup() {
        if (manager != null) manager.close();
        if (factory != null) factory.close();
    }

    @Test
    public void testCreateAccount() {
        Account account = new Account("test", "12345");

        manager.persist(account);

        Assert.assertNotNull(manager.find(Account.class, account.getId()));
    }

    @Test
    public void query() {
        Account account = new Account("test", "12345");
        manager.getTransaction().begin();

        try {
            manager.persist(account);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }


        Account found = manager.createQuery("from Account where login = :p", Account.class)
                .setParameter("p", "test")
                .getSingleResult();

        Assert.assertEquals(account.getId(), found.getId());
        Assert.assertEquals("12345", found.getPassword());
    }

    @Test
    public void criteriaBuilder() {
        Account account = new Account("test", "12345");
        manager.getTransaction().begin();

        try {
            manager.persist(account);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        CriteriaBuilder builder = factory.getCriteriaBuilder();
        CriteriaQuery<Account> query = builder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        query.where(
                builder.equal(root.get("login"), "test")
        );

        Account found = manager.createQuery(query).getSingleResult();

        Assert.assertEquals(account.getId(), found.getId());
    }
}