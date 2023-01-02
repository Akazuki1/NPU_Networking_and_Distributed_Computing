package Test;

import java.io.*;
import java.net.*;

public class Client {
    private static Socket socket;

    public void connect(int port){

        try{

            String host = "127.0.0.1";
            socket = new Socket(host, port);

            System.out.println(host +":"+port+">连接成功");


        } catch (UnknownHostException e) {
            System.err.println("Unknown Host");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException {

        Client client = new Client();
        client.connect(2021);
        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader socIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socOut = new PrintWriter(socket.getOutputStream());
        //readLine读取客户端输入的指令
        String readLine = sysIn.readLine();
        while(!readLine.equals("bye")){

            //发送指令
            socOut.println(readLine);
            socOut.flush();
            //接收服务器返回消息
            String output = socIn.readLine();

            //每次服务器端都多发一个空行。如果读到服务器发来的空行就说明读完了，继续接收下一个用户输入的指令
            while(!output.equals("")){
                System.out.println(output);
                if(output.startsWith("开始传输文件")){
                    String fileName = output.substring(7);
                    output = socIn.readLine();
                    int lengthOfFile = Integer.parseInt(output);
                    System.out.println("文件大小:" + lengthOfFile);

                    byte[] buf = new byte[1024];

                    DatagramSocket dataSkt = new DatagramSocket(2021, InetAddress.getLocalHost());
                    DatagramPacket dataPkt = new DatagramPacket(buf, 1024);
                    String pathName = "E:\\NetWork\\Test_Receive";
                    FileOutputStream fout = new FileOutputStream(pathName + File.separator + fileName);

                    socOut.println("Ready To Receive\r\n");
                    socOut.flush();
                    //循环接受数据报，循环次数等于文件大小/每个数据包大小+1
                    for(int i = 0; i < lengthOfFile /buf.length + 1; i++){
                        dataSkt.receive(dataPkt);
                        buf = dataPkt.getData();
                        //写入文件
                        fout.write(buf, 0, 1024);
                    }
                    System.out.println("文件传输完成");
                    dataSkt.close();

                }
                //如果服务器发来了空行，即output.equals("") == true 则说明服务器端消息全部读完，将在while处跳出循环并继续读取用户输入的指令
                output = socIn.readLine();
            }
            //
            readLine = sysIn.readLine();
        }
        System.out.println("Disconnected");
    }
}
