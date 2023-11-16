package ru.anxidy.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.anxidy.entities.Account;
import ru.anxidy.entities.Message;

import java.util.List;

@Repository
public class MessagesDAO {
    private final EntityManager manager;

    @Autowired
    public MessagesDAO(EntityManager manager) {
        this.manager = manager;
    }

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

    public List<Message> findByReceiver(Account receiver) {
        return manager.createQuery("from Message where receiver =:r", Message.class)
                .setParameter("r", receiver)
                .getResultList();
    }

    public List<Account> getDialogues(Account user) {
        return manager.createQuery(
                        "select sender from Message where receiver=:u union select receiver from Message where sender=:u order by time",
                        Account.class)
                .setParameter("u", user)
                .getResultList();
    }

    public List<Message> getDialogueMessages(Account sender, Account receiver) {
        return manager.createQuery("from Message where  sender =:s and receiver =:r", Message.class)
                .setParameter("s", sender)
                .setParameter("r", receiver)
                .getResultList();
    }
}

