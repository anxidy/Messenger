package ru.anxidy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anxidy.dao.AccountsDAO;
import ru.anxidy.dao.ChatDAO;
import ru.anxidy.dao.MessagesDAO;
import ru.anxidy.entities.Account;
import ru.anxidy.entities.Chat;
import ru.anxidy.entities.Message;

import java.util.List;

@Service
public class ChatService {

    private final ChatDAO chatDao;
    private final MessagesDAO messagesDao;
    private final AccountsDAO accountsDao;

    @Autowired
    public ChatService(ChatDAO chatDao, MessagesDAO messagesDao, AccountsDAO accountsDao) {
        this.chatDao = chatDao;
        this.messagesDao = messagesDao;
        this.accountsDao = accountsDao;
    }

    public List<Message> getChatMessages(int id) {
        return chatDao.getChatMessages(id);
    }

    public Chat findChat(int chatId){
        return chatDao.findById(chatId);
    }

    public Account getSender(long accountId){
        return accountsDao.findById(accountId);
    }

    public void postMessage(Message message){
        messagesDao.create(message);
    }
}
