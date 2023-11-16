package ru.anxidy.web;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.anxidy.dao.AccountsDAO;
import ru.anxidy.dao.MessagesDAO;
import ru.anxidy.entities.Account;
import ru.anxidy.entities.Message;

import java.util.Date;

@Component
public class StartupListener{

    private final AccountsDAO accountsDAO;
    private final MessagesDAO messagesDAO;
    private final EntityManager manager;

    @Autowired
    public StartupListener(AccountsDAO accountsDAO, MessagesDAO messagesDAO, EntityManager manager) {
        this.accountsDAO = accountsDAO;
        this.messagesDAO = messagesDAO;
        this.manager = manager;
    }

    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent event) {

        Account testAccount1 = new Account("login", "12345");
        Account testAccount2 = new Account("log", "12234");
        manager.getTransaction().begin();

        try (manager) {
            accountsDAO.create(testAccount1);
            accountsDAO.create(testAccount2);

            for (int i = 0; i < 10; i++) {
                Message message = new Message("test", new Date(), testAccount1, testAccount2);
                messagesDAO.create(message);
            }

            manager.getTransaction().commit();
        }
    }
}