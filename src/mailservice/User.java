package mailservice;

import java.util.ArrayList;
import java.util.List;
public class User {
    private String username;
    private List<Message> inbox;
    private List<Message> outbox;
    private List<Message> spam;
    private SpamFilter spamFilter;

    public User(String username, SpamFilter spamFilter) {
        this.username = username;
        this.spamFilter = spamFilter;
        this.inbox = new ArrayList<>();
        this.outbox = new ArrayList<>();
        this.spam = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Message> getInbox() {
        return new ArrayList<>(inbox);
    }

    public List<Message> getOutbox() {
        return new ArrayList<>(outbox);
    }

    public List<Message> getSpam() {
        return new ArrayList<>(spam);
    }

    public SpamFilter getSpamFilter(){
        return spamFilter;
    }

    public void setSpamFilter(SpamFilter spamFilter) {
        this.spamFilter = spamFilter;
    }

    public void sendMessage(String caption, String text, User receiver) {
        Message message = new Message(caption, text, this, receiver);
        outbox.add(message);
        if (spamFilter.isSpam(message)) receiver.spam.add(message);
        else receiver.inbox.add(message);
    }
}