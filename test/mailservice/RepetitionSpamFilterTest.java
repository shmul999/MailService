package mailservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RepetitionSpamFilterTest {

    @Test
    void testIsSpamWithVariousRepetitions() {
        User sender = new User("sender", new SimpleSpamFilter());
        User receiver = new User("receiver", new SimpleSpamFilter());

        RepetitionSpamFilter filter = new RepetitionSpamFilter(3);

        Message spamMessage = new Message("test", "naga naga naga naga", sender, receiver);
        assertTrue(filter.isSpam(spamMessage));

        Message limitMessage = new Message("test", "nega nega nega", sender, receiver);
        assertFalse(filter.isSpam(limitMessage));

        Message normalMessage = new Message("test", "different words here", sender, receiver);
        assertFalse(filter.isSpam(normalMessage));

        Message mixedMessage = new Message("test", "a a a b b b c c c", sender, receiver);
        assertFalse(filter.isSpam(mixedMessage));
    }
}