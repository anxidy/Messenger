package ru.anxidy.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.anxidy.entities.Account;
import ru.anxidy.entities.Message;

import java.util.List;

@Repository
public class MessagesDAO {

    @PersistenceContext
    private EntityManager manager;

    public MessagesDAO() {
    }

    public MessagesDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void create(Message message) {
        manager.persist(message);
    }

    public Message findById(int id) {
        return manager.find(Message.class, id);
    }

    public List<Message> findBySender(Account sender) {
        return manager.createQuery("from Message where sender =:s", Message.class)
                .setParameter("s", sender)
                .getResultList();
    }
}

