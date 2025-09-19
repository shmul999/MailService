package mailservice;

import java.util.HashMap;
import java.util.Map;
public class RepetitionSpamFilter implements SpamFilter {
    private int limit;
    public  RepetitionSpamFilter(int limit){
        this.limit = limit;
    }

    @Override
    public boolean isSpam(Message message){
        String[] text = message.getText().toLowerCase().split("\\s");
        Map<String, Integer> counter = new HashMap<>();

        for(int i = 0; i < text.length; ++i){
            int count = counter.getOrDefault(text[i], 0) + 1;
            counter.put(text[i], count);
            if(counter.get(text[i]) > limit) return true;
        }
        return false;
    }
}