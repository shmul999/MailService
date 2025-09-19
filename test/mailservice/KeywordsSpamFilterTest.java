package mailservice;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class KeywordsSpamFilterTest {

    @Test
    void testIsSpamKeywords() {
        User sender = new User("sender", new SimpleSpamFilter());
        User receiver = new User("receiver", new SimpleSpamFilter());
        List<String> keywords = Arrays.asList("niga", "nega", "naga");
        KeywordsSpamFilter filter = new KeywordsSpamFilter(keywords);


        Message spamMessage1 = new Message("Normal", "This is niga", sender, receiver);
        assertTrue(filter.isSpam(spamMessage1));

        Message spamMessage2 = new Message("Important notice", "Nigga", sender, receiver);
        assertTrue(filter.isSpam(spamMessage2));

        Message normalMessage = new Message("Normal", "naga", sender, receiver);
        assertFalse(filter.isSpam(normalMessage));
    }
}