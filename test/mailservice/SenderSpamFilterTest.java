package mailservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SenderSpamFilterTest {

    @Test
    void testIsSpamSender() {
        User spamSender = new User("spammer", new SimpleSpamFilter());
        User otherSender = new User("not_spammer", new SimpleSpamFilter());
        User receiver = new User("I", new SimpleSpamFilter());

        SenderSpamFilter filter = new SenderSpamFilter(spamSender);

        Message spamMessage = new Message("Hi", "Message", spamSender, receiver);
        assertTrue(filter.isSpam(spamMessage));

        Message normalMessage = new Message("Hi", "Message", otherSender, receiver);
        assertFalse(filter.isSpam(normalMessage));
    }
}