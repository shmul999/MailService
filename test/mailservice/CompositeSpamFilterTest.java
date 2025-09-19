package mailservice;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CompositeSpamFilterTest {

    @Test
    void testCompositeFilterWithMultipleFilters() {
        User sender = new User("sender", new SimpleSpamFilter());
        User receiver = new User("receiver", new SimpleSpamFilter());

        SpamFilter keywordFilter = new KeywordsSpamFilter(List.of("spam"));
        SpamFilter senderFilter = new SenderSpamFilter(sender);

        CompositeSpamFilter compositeFilter = new CompositeSpamFilter(List.of(keywordFilter, senderFilter));

        Message keywordSpam = new Message("test", "This is spam", sender, receiver);
        assertTrue(compositeFilter.isSpam(keywordSpam));

        Message senderSpam = new Message("hi", "Normal", sender, receiver);
        assertTrue(compositeFilter.isSpam(senderSpam));

        User otherSender = new User("other", new SimpleSpamFilter());
        Message normalMessage = new Message("Hello", "Normal", otherSender, receiver);
        assertFalse(compositeFilter.isSpam(normalMessage));
    }
}