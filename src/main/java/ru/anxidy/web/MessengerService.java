package ru.anxidy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anxidy.dao.AccountsDAO;
import ru.anxidy.entities.Chat;

import java.util.List;

@Service
public class MessengerService {

    private final AccountsDAO accountsDao;

    @Autowired
    public MessengerService(AccountsDAO accountsDao) {
        this.accountsDao = accountsDao;
    }

    public List<Chat> getChats(long accountId) {
        return accountsDao.getChats(accountId);
    }
}