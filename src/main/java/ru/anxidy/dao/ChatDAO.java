package ru.anxidy.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.anxidy.entities.Account;
import ru.anxidy.entities.Chat;
import ru.anxidy.entities.Message;

import java.util.List;

@Repository
public class ChatDAO {

    @PersistenceContext
    private EntityManager manager;

    public ChatDAO() {}
    public ChatDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void create(Chat chat) {
        manager.persist(chat);
    }

    public Chat findById(int chatId) {
        return manager.find(Chat.class, chatId);
    }

    public List<Account> getChatMembers(int chatId) {
        return manager.createQuery("select r from Account r join r.chats c where c.id =:cid", Account.class)
                .setParameter("cid", chatId)
                .getResultList();
    }

    public List<Message> getChatMessages(int chatId) {
        return manager.createQuery("select m from Message m join m.chat c where c.id =:cid", Message.class)
                .setParameter("cid", chatId)
                .getResultList();
    }
}
