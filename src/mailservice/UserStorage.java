package mailservice;

import java.util.ArrayList;
import java.util.List;
public class UserStorage {
    private List<User> users = new ArrayList<>();

    public UserStorage(List<User> users){
        this.users = users;
    }
    public void addUser(User user){
        users.add(user);
    }

    public User getUser(String name){
        for(int i = 0; i < users.size(); ++i){
            User cur_user = users.get(i);
            if(cur_user.getUsername().equals(name)){
                return cur_user;
            }
        }
        return null;
    }

    public boolean isUser(String name){
        for(int i = 0; i < users.size(); ++i){
            User cur_user = users.get(i);
            if(cur_user.getUsername().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(){
        if(users.isEmpty()) return true;
        return false;
    }

    public List<User> getAllUsers(){
        return users;
    }
}