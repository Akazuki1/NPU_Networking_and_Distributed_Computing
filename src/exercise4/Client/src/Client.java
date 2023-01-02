/**
 * @author:Zhang_Haolin
 * @date:2022/11/11
 * @version:1.0.0
 * @description:
 */
import com.todolist.*;

import java.util.List;
import java.util.Scanner;

public class Client {

    private static Scanner keyboard;
    public static void main(String[] args) throws ParseException_Exception {

        keyboard = new Scanner(System.in);
        ServerService serverService = new ServerService();
        Todolist port = serverService.getPort();

        new Client().run(port);
    }

    /**
     * 客户端运行
     * @param port
     * @throws ParseException_Exception
     */
    private void run(Todolist port) throws ParseException_Exception {

        System.out.println("欢迎使用待办事务助手\n");
        while(true){
            help();
            String request = keyboard.nextLine();

            String[] splitedLine;
            if(request.startsWith("register")){
                splitedLine = request.split(" ");
                if(port.register(splitedLine[1], splitedLine[2])){
                    System.out.println("注册成功! ");
                } else {
                    System.out.println("注册失败! 该用户名已经被注册过");
                }

            } else if(request.startsWith("add")){
                splitedLine = request.split(" ");
                int i = port.add(splitedLine[1], splitedLine[2], splitedLine[3], splitedLine[4], splitedLine[5]);
                if(i == -1){
                    System.out.println("用户名和密码错误!");
                } else if (i == -2){
                    System.out.println("该时段已被占用!");
                } else if(i == 0){
                    System.out.println("添加成功!");
                }
            } else if(request.startsWith("query")){
                int cnt = 0;
                int out = 0;
                splitedLine = request.split(" ");
                List<String> tempList = port.query(splitedLine[1], splitedLine[2], splitedLine[3], splitedLine[4]);
                if(tempList != null){
                    for(String temp : tempList){
                        cnt++;
                        out++;
                        if(out % 4 == 1){
                            System.out.println("ID:");
                        } else if(out%4 ==2){
                            System.out.println("起始时间:");
                        } else if(out%4 ==3){
                            System.out.println("终止时间:");
                        } else if(out % 4 ==0){
                            System.out.println("描述:");
                        }
                        System.out.println(temp);
                    }
                    if(cnt == 0){
                        System.out.println("待办事项为空!");
                    }
                } else {
                    System.out.println("待办事项为空!");
                }
            } else if(request.startsWith("delete")){
                splitedLine = request.split(" ");
                if(port.delete(splitedLine[1], splitedLine[2], Integer.parseInt(splitedLine[3]))){
                    System.out.println("删除成功!");
                } else {
                    System.out.println("删除失败!");
                }
            } else if(request.startsWith("clear")){
                splitedLine = request.split(" ");
                if(port.clear(splitedLine[1], splitedLine[2])){
                    System.out.println("清除成功!");
                } else{
                    System.out.println("清除失败!");
                }
            } else{
                System.out.println("指令错误，请重新输入");
            }

        }
    }

    private void help(){
        System.out.println();
        System.out.println("用户注册: "  + "\n" + "register username password" + "");
        System.out.println("添加项目: " + "\n"  + "add username password startTime endTime description");
        System.out.println("查询项目: \n" + "query username password startTime endTime ");
        System.out.println("时间格式: \nyyyy-mm-dd-hh-mm");
        System.out.println("删除某个项目:\n" + "delete username password listId");
        System.out.println("清空所有项目:\n" + "clear username password");
        System.out.println("退出\n" + "quit");

    }
}
