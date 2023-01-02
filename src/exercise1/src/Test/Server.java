package Test;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket socket;
        ServerSocket server = new ServerSocket(2021);
        System.out.println("服务器启动");
        while(true){
            socket = server.accept();

            ServerThread serverThread = new ServerThread(socket);

            serverThread.start();
        }
    }
}
