package mailservice;

public class SenderSpamFilter implements SpamFilter {
    private final User user;
    public SenderSpamFilter(User user){
        this.user = user;
    }
    @Override
    public  boolean isSpam(Message message){
        if (message.getSender().getUsername().equals(user.getUsername())) return true;
        return false;
    }
}