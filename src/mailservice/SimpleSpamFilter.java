package mailservice;

public class SimpleSpamFilter implements SpamFilter {
    @Override
    public boolean isSpam(Message message) {
        String text = message.getText().toLowerCase();
        String caption = message.getCaption().toLowerCase();
        if (text.contains("spam") || caption.contains("spam")) return true;
        return false;
    }
}