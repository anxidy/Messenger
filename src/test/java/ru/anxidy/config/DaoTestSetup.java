package ru.anxidy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
public class DaoTestSetup {
    private AccountsDAO accountsDao;
    private ChatDAO chatDao;
    private MessagesDAO messagesDao;

    public DaoTestSetup() {}
    @Autowired
    public DaoTestSetup(AccountsDAO accountsDao, ChatDAO chatDao, MessagesDAO messagesDao) {
        this.accountsDao = accountsDao;
        this.chatDao = chatDao;
        this.messagesDao = messagesDao;
    }

    private final List<Account> accountList = new ArrayList<>();
    private final List<Message> messagesList = new ArrayList<>();
    private final List<Chat> chatList = new ArrayList<>();

    @Transactional
    public void init(){

         chatList.clear();
         messagesList.clear();
         accountList.clear();

        for (int i = 0; i < 10; i++) {
            Account account = new Account(Integer.toString(i), "12345");
            accountList.add(account);
            accountsDao.create(account);
        }

        for (int i = 0; i < 10; i++) {
            Chat chat = new Chat(Integer.toString(i), accountList);
            chatList.add(chat);
            chatDao.create(chat);
        }

        for (int i = 0; i < 10; i++) {
            Message message = new Message("test", new Date(), accountList.get(0), chatList.get(0));
            messagesList.add(message);
            messagesDao.create(message);
        }
    }

    public AccountsDAO getAccountsDao() {
        return accountsDao;
    }

    public ChatDAO getChatDao() {
        return chatDao;
    }

    public MessagesDAO getMessagesDao() {
        return messagesDao;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public List<Message> getMessagesList() {
        return messagesList;
    }

    public List<Chat> getChatList() {
        return chatList;
    }
}
