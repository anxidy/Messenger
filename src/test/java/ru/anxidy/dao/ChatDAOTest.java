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
import ru.anxidy.entities.Message;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ChatDAOTest {

    @Autowired
    private DaoTestSetup setup;

    public ChatDAOTest() {
    }

    @Before
    public void setup() {
        setup.init();
    }

    @Test
    public void testFindById() {
        Chat found = setup.getChatDao().findById(1);

        assertEquals(found.getName(), setup.getChatList().get(0).getName());
    }

    @Test
    public void testGetChatMembers() {
        List<Account> foundList = setup.getChatDao().getChatMembers(1);

        int i = 0;
        for (Account target : setup.getAccountList()) {
            System.out.println(target.getLogin());
            assertEquals(target.getLogin(), foundList.get(i).getLogin());
            i++;
        }
    }

    @Test
    public void testGetChatMessages() {
        List<Message> foundList = setup.getChatDao().getChatMessages(1);

        int i = 0;
        for (Message target : setup.getMessagesList()) {
            System.out.println(target.getMessageBody());
            assertEquals(target.getMessageBody(), foundList.get(i).getMessageBody());
            i++;
        }
    }
}