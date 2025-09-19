package mailservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testMessage() {
        User sender = new User("sender", new SimpleSpamFilter());
        User receiver = new User("receiver", new SimpleSpamFilter());
        Message message = new Message("test_message", "hahaha im testing!", sender, receiver);

        assertEquals("tets_message", message.getCaption());
        assertEquals("hahaha im testing!", message.getText());
        assertEquals(sender, message.getSender());
        assertEquals(receiver, message.getReceiver());
    }
}