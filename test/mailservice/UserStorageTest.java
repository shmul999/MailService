package mailservice;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserStorageTest {

    @Test
    void testUserStorageOperations() {
        UserStorage storage = new UserStorage(new ArrayList<>());
        User user1 = new User("user_1", new SimpleSpamFilter());
        User user2 = new User("user_2", new SimpleSpamFilter());

        storage.addUser(user1);
        storage.addUser(user2);

        assertEquals(2, storage.getAllUsers().size());
        assertTrue(storage.isUser("user_1"));
        assertTrue(storage.isUser("user_2"));
        assertFalse(storage.isUser("none"));

        assertEquals(user1, storage.getUser("user_1"));
        assertNull(storage.getUser("none"));

        assertFalse(storage.isEmpty());
        UserStorage emptyStorage = new UserStorage(new ArrayList<>());
        assertTrue(emptyStorage.isEmpty());
    }
}