package mailservice;

import java.util.List;
public class KeywordsSpamFilter implements SpamFilter {
    private final List<String> keywords;
    public KeywordsSpamFilter(List<String> keywords){
        this.keywords = keywords;
    }

    @Override
    public boolean isSpam(Message message) {
        String text = message.getText().toLowerCase();
        String caption = message.getCaption().toLowerCase();
        for(int i = 0; i < keywords.size(); ++i){
            String toCheck = keywords.get(i).toLowerCase();
            if(text.contains(toCheck) || caption.contains(toCheck)) return true;
        }
        return false;
    }
}