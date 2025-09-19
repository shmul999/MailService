package mailservice;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MailServiceIntegrationTest {

    @Test
    void testCompleteScenario() {
        List<User> users = new ArrayList<>();
        UserStorage storage = new UserStorage(users);

        Main.add("nikita", storage);
        Main.add("roma", storage);

        assertTrue(storage.isUser("nikita"));
        assertTrue(storage.isUser("roma"));

        String result = Main.send("nikita", "roma", "hello", "it's me", storage);
        assertEquals("Message from nikita sent to roma", result);

        User roma = storage.getUser("roma");
        assertEquals(1, roma.getInbox().size());
        assertEquals("hello", roma.getInbox().get(0).getCaption());

        User nikita = storage.getUser("nikita");
        assertEquals(1, nikita.getOutbox().size());
        assertEquals("it's me", nikita.getOutbox().get(0).getText());
    }
}