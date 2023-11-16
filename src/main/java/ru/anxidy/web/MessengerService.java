package ru.anxidy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anxidy.dao.AccountsDAO;
import ru.anxidy.dao.MessagesDAO;
import ru.anxidy.entities.Account;
import ru.anxidy.entities.Message;
import java.util.List;

@Service
public class MessengerService {

    private final AccountsDAO accountsDao;
    private final MessagesDAO messagesDao;

    public MessengerService(@Autowired AccountsDAO accountsDao, @Autowired MessagesDAO messagesDao) {
        this.accountsDao = accountsDao;
        this.messagesDao = messagesDao;
    }

    public List<Account> getDialogues(int accountId) {
        Account user = accountsDao.findById(accountId);
        return messagesDao.getDialogues(user);
    }

    public List<Message> getDialogueMessages(int senderId, int receiverId) {
        Account sender = accountsDao.findById(senderId);
        Account receiver = accountsDao.findById(receiverId);
        return messagesDao.getDialogueMessages(sender, receiver);
    }
}