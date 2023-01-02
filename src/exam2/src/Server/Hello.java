package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

import bean.Message;
import bean.User;
import rinterface.MessageInterface;
/**
 * @author:Zhang_Haolin
 * @date:2022/11/12
 * @version:1.0.0
 * @description:
 */

//命名为Message会与bean.Message冲突 故命名为Hello
public class Hello extends UnicastRemoteObject implements MessageInterface  {

    private static final long serialVersionUID = 1L;

    private int id = 0;
    private List<User> userList = new ArrayList<User>();
    private List<Message> messageList = new ArrayList<Message>();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected Hello() throws RemoteException {
    }


    @Override
    /**
     * 输入用户名和密码注册用户
     */
    public boolean register(String username, String password) throws RemoteException {
        if(!isExist(username)){
            userList.add( new User(username, password));
            return true;
        }
        return false;
    }

    @Override
    /**
     * 显示所有用户
     */
    public List<String> showUsers() throws RemoteException {

        List<String> users = new ArrayList<String>();
        for(User user : userList){
            users.add(user.getUsername());
        }
        return users;
    }

    @Override
    /**
     * 查询所有收信
     */
    public String[][] checkMessage(String username, String password) throws RemoteException {

        User user = findUser(username);
        if(!loginCheck(username, password)){
            return null;
        }
        List<Message> messages = new ArrayList<Message>();
        for(Message message : user.getMessageList()){
            messages.add(message);
        }
        int n = messages.size();
        String[][] str = new String[n][3];
        for(int i = 0; i < messages.size(); i++){
            str[i][0] = messages.get(i).getSender().getUsername();
            str[i][1] = simpleDateFormat.format(messages.get(i).getDate());
            str[i][2] = messages.get(i).getText();
        }
        return str;
    }

    @Override
    /**
     * 给某人留信
     */
    public int leaveMessage(String username, String password, String received, String text) throws RemoteException {

        if(!loginCheck(username, password)){
            return -1;
        }
        if(!isExist(received)){
            return -2;

        }
        System.out.println(received);
        User sender = findUser(username);
        User receiver = findUser(received);
        Message newMessage = new Message(sender, text, new Date());
        newMessage.setSender(sender);
        assert receiver != null;
        receiver.getMessageList().add(newMessage);
        messageList.add(newMessage);
        return 0;
    }

    /**
     * 登录验证
     */
    private boolean loginCheck(String name, String password){

        for(User user : userList){
            if(user.getUsername().equals(name) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查询给定用户名是否存在
     */
    private boolean isExist(String name){

        for(User user : userList){
            if(user.getUsername().equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * 根据名称查找用户并返回User实例
     * @param name
     * @return
     */
    private User findUser(String name){

        for(User user : userList){
            if(user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }


    private void sort(List<Message> messageList){
        List<Message> tempList = new ArrayList<Message>();
        for(int i = 0; i < tempList.size(); i++){
            for(int j = i; j < tempList.size(); j++){
                if(tempList.get(j).getDate().before(tempList.get(i).getDate())){
                    swap(tempList.get(i), tempList.get(j));
                }
            }
        }
    }


    /**
     * 交换两个信息
     * @param message1
     * @param message2
     */
    private void swap(Message message1, Message message2){
        User sender = message1.getSender();
        message1.setSender(message2.getSender());
        message2.setSender(sender);

        User receiver = message1.getReceiver();
        message1.setReceiver(message2.getReceiver());
        message2.setReceiver(receiver);

        Date date = message1.getDate();
        message1.setDate(message2.getDate());
        message2.setDate(date);

        String text = message1.getText();
        message1.setText(message2.getText());
        message2.setText(text);
    }

}
