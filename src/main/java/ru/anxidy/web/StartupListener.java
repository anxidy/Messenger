package ru.anxidy.web;

import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.anxidy.dao.AccountsDAO;
import ru.anxidy.dao.MessagesDAO;
import ru.anxidy.entities.Account;
import ru.anxidy.entities.Message;

import java.util.Date;

@Component
public class StartupListener {

    private final AccountsDAO accountsDAO;
    private final MessagesDAO messagesDAO;

    @Autowired
    public StartupListener(AccountsDAO accountsDAO, MessagesDAO messagesDAO) {
        this.accountsDAO = accountsDAO;
        this.messagesDAO = messagesDAO;
    }

    @EventListener
    @Transactional
    public void handleContextRefreshEvent(ContextRefreshedEvent event) {

        Account testAccount1 = new Account("login", "12345");
        Account testAccount2 = new Account("log", "12234");

        try {
            accountsDAO.findByLogin("login");
        } catch (NoResultException notFound) {
            accountsDAO.create(testAccount1);

        }

        try {
            accountsDAO.findByLogin("log");
        } catch (NoResultException notFound) {
            accountsDAO.create(testAccount2);
        }

        try {
            messagesDAO.getDialogueMessages(testAccount1, testAccount2);
        } catch (NoResultException notFound) {
            for (int i = 0; i < 10; i++) {
                Message message = new Message("test", new Date(), testAccount1, testAccount2);
                messagesDAO.create(message);
            }
        }
    }
}