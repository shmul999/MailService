package mailservice;

public interface SpamFilter {
    boolean isSpam(Message message);
}