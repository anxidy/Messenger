package ru.anxidy.web;

import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.anxidy.dao.AccountsDAO;
import ru.anxidy.dao.ChatDAO;
import ru.anxidy.dao.MessagesDAO;
import ru.anxidy.entities.Account;
import ru.anxidy.entities.Chat;
import ru.anxidy.entities.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class StartupListener {

    private final AccountsDAO accountsDAO;
    private final MessagesDAO messagesDAO;
    private final ChatDAO chatDAO;

    @Autowired
    public StartupListener(AccountsDAO accountsDAO, MessagesDAO messagesDAO, ChatDAO chatDAO) {
        this.accountsDAO = accountsDAO;
        this.messagesDAO = messagesDAO;
        this.chatDAO = chatDAO;
    }

    @EventListener
    @Transactional
    public void handleContextRefreshEvent(ContextRefreshedEvent event) {

        Account testAccount = new Account("login", "12345");

        try {
            accountsDAO.findByLogin("login");
        } catch (NoResultException notFound) {
            accountsDAO.create(testAccount);
            List<Account> receivers = new ArrayList<>();

            for (int i = 1; i < 10; i++) {
                Account account = new Account("login" + i, "12345");
                accountsDAO.create(account);
                receivers.add(account);
            }

            for (Account account : receivers) {
                ArrayList <Account> dialogue = new ArrayList<>();
                dialogue.add(testAccount);
                dialogue.add(account);

                Chat testChat = new Chat(account.getLogin(), dialogue);
                chatDAO.create(testChat);

                Message message = new Message("Hi!", new Date(), testAccount, testChat);
                messagesDAO.create(message);
            }
            receivers.add(testAccount);

            Chat testChat = new Chat("Group chat", receivers);
            chatDAO.create(testChat);

            for (int i = 0; i < 10; i++) {
                Message message = new Message("test", new Date(), testAccount, testChat);
                messagesDAO.create(message);
            }
        }
    }
}