import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author:Zhang_Haolin
 * @date:2022/10/21
 * @version:1.0.0
 * @description:
 */

public class Server {

    private static String path;
    private static ServerSocket serverSocket;
    public void startServer(String path) throws IOException {
        Socket socket;
        /**
         * 循环接受连接请求并交给线程实例处理
         */
        while(true){
            socket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(socket, path);
            serverThread.start();
        }
    }

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(80);
        path = args[0];
        File file = new File(path);
        /**
         * 路径合法性检查
         */
        if(file.isDirectory()){
            Server server = new Server();
            server.startServer(path);
        }
    }

}
