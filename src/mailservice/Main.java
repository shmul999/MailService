package mailservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        UserStorage userStorage = new UserStorage(users);
        Scanner scan = new Scanner(System.in);
        while(true){
            String command = scan.nextLine();
            if (command.equals("add")){
                System.out.print("Enter user name: ");
                String name = scan.nextLine();
                System.out.println(add(name, userStorage));
            }else if(command.equals("list")){
                List<String> cur_users = list(userStorage);
                for(String user: cur_users){
                    System.out.println(user);
                }
            }else if(command.equals("send")){
                System.out.print("Enter sender name: ");
                String sender_name = scan.nextLine();
                System.out.print("Enter receiver name: ");
                String receiver_name = scan.nextLine();
                System.out.print("Enter Caption: ");
                String title = scan.nextLine();
                System.out.print("Enter text: ");
                String text = scan.nextLine();
                System.out.println(send(sender_name, receiver_name, title, text, userStorage));
            }else if(command.equals("inbox")){
                System.out.print("Enter user name: ");
                String name = scan.nextLine();
                List<String> inbox = inbox(name, userStorage);
                for(String message: inbox){
                    System.out.println(message);
                }
            }else if(command.equals("setfilter")){
                System.out.print("Enter user name: ");
                String name = scan.nextLine();
                System.out.print("Enter filter type: ");
                String type;
                while(!(type = scan.nextLine()).equalsIgnoreCase("done")){
                    if(type.equals("simple")){
                        SpamFilter filter = new SimpleSpamFilter();
                        System.out.println(setfilter(name, filter, userStorage));
                    }else if (type.equalsIgnoreCase("sender")){
                        System.out.print("Enter the user to avoid: ");
                        String avoid = scan.nextLine();
                        SpamFilter filter = new SenderSpamFilter(userStorage.getUser(avoid));
                        System.out.println(setfilter(name, filter, userStorage));
                    }else if (type.equalsIgnoreCase("keywords")){
                        List<String> keywords = new ArrayList<>();
                        System.out.print("Enter keywords: ");
                        String line = scan.nextLine();
                        String[] array = line.split(" ");
                        for (String item : array) {
                            keywords.add(item);
                        }
                        SpamFilter filter = new KeywordsSpamFilter(keywords);
                        System.out.println(setfilter(name, filter, userStorage));
                    }else if(type.equalsIgnoreCase("repetition")){
                        System.out.println("Enter the limit: ");
                        int limit = scan.nextInt();
                        SpamFilter filter = new RepetitionSpamFilter(limit);
                        System.out.println(setfilter(name, filter, userStorage));
                    }else{
                        System.out.println("ERROR: No such a filter");
                    }
                    System.out.print("Enter filter type: ");
                }
            }else{
                System.out.println("ERROR: No such a command");
            }
        }
    }
    public static String add(String name, UserStorage userStorage) {
        if (userStorage.isUser(name)) {
            return "User " + name + " already exists";
        }

        User newUser = new User(name, new SimpleSpamFilter());
        userStorage.addUser(newUser);
        return "User " + name + " added";
    }
    public static List <String> list(UserStorage userStorage){
        List <User> storage = userStorage.getAllUsers();
        List <String> users = new ArrayList<>();
        for(int i = 0;i < storage.size();++i){
            User cur_user = storage.get(i);
            users.add(cur_user.getUsername());
        }
        return users;
    }

    public static String send(String sender_name, String receiver_name, String title, String text, UserStorage userStorage){
        if (!userStorage.isUser(sender_name)) {
            return "User " + sender_name + " not exists";
        }
        if (!userStorage.isUser(receiver_name)) {
            return "User " + receiver_name + " not exists";
        }

        User sender = userStorage.getUser(sender_name);
        User receiver = userStorage.getUser(receiver_name);
        sender.sendMessage(title, text, receiver);
        return "Message from " + sender_name + " sent to " + receiver_name;
    }

    public static List<String> inbox(String new_user, UserStorage userStorage){
//        if(userStorage.isUser(new_user)) return null;
        User user = userStorage.getUser(new_user);
        List<Message> inboxListMessages = user.getInbox();
        List<String> inboxList = new ArrayList<>();
        SpamFilter filter = user.getSpamFilter();
        for(Message message: inboxListMessages){
            if (!filter.isSpam(message)){
                inboxList.add("================================" + "\n" + message.getCaption() + "\n" + message.getText() + "\n" + "================================");
            }
        }
        return inboxList;
    }

    public static List<String> outbox(String new_user, UserStorage userStorage){
        if(userStorage.isUser(new_user)) return null;
        User user = userStorage.getUser(new_user);
        List<Message> outboxListMessages = user.getOutbox();
        List<String> outboxList = new ArrayList<>();
        SpamFilter filter = user.getSpamFilter();
        for(Message message: outboxListMessages){
            if (!filter.isSpam(message)){
                outboxList.add("================================" + "\n" + message.getCaption() + "\n" + message.getText() + "\n" + "================================");
            }
        }
        return outboxList;
    }

    public static List<String> spam(String new_user, UserStorage userStorage){
        if(userStorage.isUser(new_user)) return null;
        User user = userStorage.getUser(new_user);
        List<Message> spamListMessages = user.getInbox();
        List<String> spamList = new ArrayList<>();
        SpamFilter filter = user.getSpamFilter();
        for(Message message: spamListMessages){
            if (filter.isSpam(message)){
                spamList.add("================================" + "\n" + message.getCaption() + "\n" + message.getText() + "\n" + "================================");
            }
        }
        return spamList;
    }

    public static String setfilter(String new_user, SpamFilter filter, UserStorage userStorage){
        if(userStorage.isUser(new_user)) return null;
        User user = userStorage.getUser(new_user);
        user.setSpamFilter(filter);
        return "Filter was successfully set";
    }

}