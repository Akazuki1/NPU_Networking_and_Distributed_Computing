package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import bean.Meeting;
import bean.User;
import rface.HelloInterface;
/**
 * @author:Zhang_Haolin
 * @date:2022/11/2
 * @version:1.0.0
 * @description:
 */
public class HelloClient {
	public static void main(String[] argv) {
		try {
			HelloInterface hello = (HelloInterface) Naming.lookup("Hello");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line;
			String[] splitedLine;
			// 如果要从另一台启动了RMI注册服务的机器上查找hello实例
			// HelloInterface hello =
			// (HelloInterface)Naming.lookup("//192.168.1.105:1099/Hello");

			// 调用远程方法


			while(true){
				try{
					System.out.println();
					System.out.println("RMI Menu:");
					System.out.println("\t1. register [username] [password]");
					System.out.println("\t示例：register user1 1234");
					System.out.println("\t2. add [username] [password] [otherusername] [start] [end] [title]");
					System.out.println("\t示例：add user1 1234 user2 2022-11-04-10:00 2022-11-04-12:00 meeting1");
					System.out.println("\t3. query [username] [password] [start] [end]");
					System.out.println("\t示例：query user1 1234 2022-11-04-10:00 2022-11-04-12:00");
					System.out.println("\t4. delete [username] [password] [meetingid]");
					System.out.println("\t示例：delete user1 1234 0");
					System.out.println("\t5. clear [username] [password]");
					System.out.println("\t示例：clear user1 1234");
					System.out.println();
					String request = in.readLine();

					if(request.startsWith("register")){
						splitedLine = request.split(" ");
						if(hello.register(splitedLine[1], splitedLine[2])){
							System.out.println("注册成功! ");
						} else {
							System.out.println("注册失败! 该用户名已经被注册过");
						}

					} else if(request.startsWith("add")){
						splitedLine = request.split(" ");
						int i = hello.add(splitedLine[1], splitedLine[2], splitedLine[3], splitedLine[4], splitedLine[5], splitedLine[6]);
						if(i == -1){
							System.out.println("用户名和密码错误!");
						} else if (i == -2){
							System.out.println("参与者与主持人不能为同一个人!");
						} else if(i == -3){
							System.out.println("参与者不存在，请重新输入!");
						} else if(i == -4){
							System.out.println("您在该时段已有一个会议!");
						} else if(i == -5){
							System.out.println("另一个与会者在该时段已有一个会议!");
						} else if(i == 0){
							System.out.println("添加成功!");
						}
					} else if(request.startsWith("query")){
						int cnt = 0;
						splitedLine = request.split(" ");
						String[][] tempList = hello.query(splitedLine[1], splitedLine[2], splitedLine[3], splitedLine[4]);
						if(tempList != null){
							for(String[] temp : tempList){
								cnt++;
								System.out.println("ID: "+temp[0]);
								System.out.println("标题: "+temp[1]);
								System.out.println("与会者: "+temp[2]);
								System.out.println("开始时间: "+temp[3]);
								System.out.println("结束时间: "+temp[4]);
							}
							if(cnt == 0){
								System.out.println("预定会议为空!");
							}
						} else {
							System.out.println("预定会议为空!");
						}
					} else if(request.startsWith("delete")){
						splitedLine = request.split(" ");
						if(hello.delete(splitedLine[1], splitedLine[2], Integer.parseInt(splitedLine[3]))){
							System.out.println("删除成功!");
						} else {
							System.out.println("删除失败!");
						}
					} else if(request.startsWith("clear")){
						splitedLine = request.split(" ");
						if(hello.clear(splitedLine[1], splitedLine[2])){
							System.out.println("清除成功!");
						} else{
							System.out.println("清除失败!");
						}
					} else{
						System.out.println("指令错误，请重新输入");
						System.out.println("RMI Menu:");
						System.out.println("\t1. register [username] [password]");
						System.out.println("\t2. add [username] [password] [otherusername] [start] [end] [title]");
						System.out.println("\t3. query [username] [password] [start] [end]");
						System.out.println("\t4. delete [username] [password] [meetingid]");
						System.out.println("\t5. clear [username] [password]");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
