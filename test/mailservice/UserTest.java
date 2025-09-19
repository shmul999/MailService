package mailservice;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserSending() {
        User sender = new User("sender", new SimpleSpamFilter());
        User receiver = new User("receiver", new SimpleSpamFilter());

        assertEquals("sender", sender.getUsername());
        assertTrue(sender.getInbox().isEmpty());
        assertTrue(sender.getOutbox().isEmpty());
        assertTrue(sender.getSpam().isEmpty());

        sender.sendMessage("hi", "a test", receiver);

        assertEquals(1, sender.getOutbox().size());
        assertEquals("hi", sender.getOutbox().get(0).getCaption());

        assertEquals(1, receiver.getInbox().size());
        assertEquals("a test", receiver.getInbox().get(0).getText());
    }

    @Test
    void testSpamFilter() {
        User user = new User("user", new SimpleSpamFilter());
        SpamFilter newFilter = new KeywordsSpamFilter(List.of("spam"));

        user.setSpamFilter(newFilter);
        assertEquals(newFilter, user.getSpamFilter());
    }
}