package mailservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleSpamFilterTest {

    @Test
    void testIsSpam() {
        User sender = new User("sender", new SimpleSpamFilter());
        User receiver = new User("receiver", new SimpleSpamFilter());
        SimpleSpamFilter filter = new SimpleSpamFilter();

        Message spamMessage1 = new Message("ok", "This is spam", sender, receiver);
        assertTrue(filter.isSpam(spamMessage1));

        Message spamMessage2 = new Message("This is spam", "Normal content", sender, receiver);
        assertTrue(filter.isSpam(spamMessage2));

        Message normalMessage = new Message("ok", "Normal content", sender, receiver);
        assertFalse(filter.isSpam(normalMessage));
    }
}