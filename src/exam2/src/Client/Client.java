package Client;

import bean.User;
import rinterface.MessageInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

/**
 * @author:Zhang_Haolin
 * @date:2022/11/12
 * @version:1.0.0
 * @description:
 */
public class Client {

    private static Scanner keyboard;

    public static void main(String[] args){
        try{
            MessageInterface message = (MessageInterface) Naming.lookup("message");
            keyboard = new Scanner(System.in);
            String line;
            String request;
            String[] splitedLine;

            System.out.println("欢迎使用留言系统");
            help();
            // 调用远程方法
            while(true){
                try{
                    request = keyboard.nextLine();
                    splitedLine = request.split(" ");
                    if(splitedLine[0].equals("register")){
                        if(message.register(splitedLine[1], splitedLine[2])){
                            System.out.println("注册成功！");
                        } else {
                            System.out.println("注册失败，该名称已被占用！");
                        }
                    } else if(splitedLine[0].equals("showusers")){
                        List<String> users = message.showUsers();
                        for(String str : users){
                            System.out.println(str);
                        }
                        System.out.println();
                        /*
                        该方法只能查找收信人收到的消息，不能查找送信人发送的消息
                         */
                    } else if(splitedLine[0].equals("check")){
                        int cnt = 0;
                        String[][] str = message.checkMessage(splitedLine[1], splitedLine[2]);
                        if(str != null){
                            for(String[] temp : str){
                                cnt++;
                                System.out.println("送信人: "+temp[0]);
                                System.out.println("时间: "+temp[1]);
                                System.out.println("内容 "+temp[2]);
                                System.out.println();
                            }
                            if(cnt == 0){
                                System.out.println("该用户还没有收到任何消息!");
                            }
                        } else
                            {
                                System.out.println("用户名或密码错误!");
                            }
                    } else if(splitedLine[0].equals("leave")){
                        int i = message.leaveMessage(splitedLine[1], splitedLine[2], splitedLine[3], splitedLine[4]);
                        if(i == -1){
                            System.out.println("用户名或密码错误！");
                        } else if(i == -2){
                            System.out.println("收信人不存在！");
                        } else{
                            System.out.println("留言成功！");
                        }
                    } else {
                        System.out.println("指令错误，请重新输入！");
                        help();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 帮助菜单
     */
    private static void help(){
        StringBuilder menu = new StringBuilder();
        menu.append("tregister(username, password)\n");
        menu.append("tshowusers()\n");
        menu.append("check(username, password)\n");
        menu.append("leave(username, password, receiver_name, message_text)\n");
        menu.append("\r\n");
        System.out.println(menu);
    }
}
