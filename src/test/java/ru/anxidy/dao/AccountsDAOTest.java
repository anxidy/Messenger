package ru.anxidy.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.anxidy.config.DaoTestSetup;
import ru.anxidy.config.TestConfig;
import ru.anxidy.entities.Account;
import ru.anxidy.entities.Chat;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountsDAOTest {

    @Autowired
    private DaoTestSetup setup;

    public AccountsDAOTest() {
    }

    @Before
    public void setup() {
        setup.init();
    }

    @Test
    public void findById() {
        Account found = setup.getAccountsDao().findById(1);

        assertEquals(found.getLogin(), setup.getAccountList().get(0).getLogin());
    }

    @Test
    public void findByLogin() {
        Account found = setup.getAccountsDao().findByLogin("0");

        assertEquals(found.getPassword(), setup.getAccountList().get(0).getPassword());
    }

    @Test
    public void findByLoginAndPassword() {
        Account found = setup.getAccountsDao().findByLoginAndPassword("0", "12345");

        assertEquals(found.getId(), setup.getAccountList().get(0).getId());
    }

    @Test
    public void testGetChats() {
        List<Chat> foundList = setup.getAccountsDao().getChats(1);

        int i = 0;
        for (Chat target : setup.getChatList()) {
            assertEquals(target.getName(), foundList.get(i).getName());
            i++;
        }
    }
}