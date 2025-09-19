package mailservice;

import java.util.List;
public class CompositeSpamFilter implements SpamFilter {
    private List<SpamFilter> used;
    public CompositeSpamFilter(List<SpamFilter> used){
        this.used = used;
    }
    @Override
    public boolean isSpam(Message message){
        for(int i = 0; i < used.size();++i){
            SpamFilter filter = used.get(i);
            if(filter.isSpam(message)) return true;
        }
        return false;
    }
}