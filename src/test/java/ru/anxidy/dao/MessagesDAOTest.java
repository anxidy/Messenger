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
import ru.anxidy.entities.Message;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MessagesDAOTest {

    @Autowired
    private DaoTestSetup setup;

    public MessagesDAOTest() {
    }

    @Before
    public void setup() {
        setup.init();
    }

    @Test
    public void findById() {
        Message found = setup.getMessagesDao().findById(1);

        assertEquals(found.getMessageBody(), setup.getMessagesList().get(0).getMessageBody());
    }

    @Test
    public void findBySender() {
        List<Message> found = setup.getMessagesDao().findBySender(
                setup.getAccountList().get(0));

        int i = 0;
        for (Message message : found) {
            System.out.println(message.getMessageBody());
            assertEquals(message.getMessageBody(), setup.getMessagesList().get(i).getMessageBody());
            i++;
        }
    }
}