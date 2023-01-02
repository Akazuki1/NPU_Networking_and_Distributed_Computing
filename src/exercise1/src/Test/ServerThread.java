package Test;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class ServerThread extends Thread{

    String pathName = "E:\\NetWork\\Test_Send";
    File file = new File(pathName);
    Socket socket;
    String str;


    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
    }

    BufferedReader socIn;

    PrintWriter socOut;

    public void run() {

        try{
            socIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socOut = new PrintWriter(socket.getOutputStream());
            while(true){
                str = socIn.readLine();
                if(str.equals("bye"))
                    break;
                else if(str.equals("ls")){
                    list();
                }
                else if(str.startsWith("cd")){
                    changeDirectory();
                }
                else if(str.startsWith("get ")){
                    get();
                }
                else{
                    socOut.println("Unknown Command");
                    socOut.write("\r\n");
                    socOut.flush();
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error detected, connection fail\n");
        }

    }

    /**
     * 显示目录下的文件夹或者文件
     */
    private void list(){
        File temp;
        String[] files = file.list();
        String[] s = new String[files.length];

        for(int i = 0; i <s.length; i++){

            temp = new File(pathName + File.separator + files[i]);

            if(temp.isDirectory()){
                s[i] = "<dir>";
            }else{
                s[i] = "<file>";
            }
            s[i] = s[i] +"\t\t"+ files[i] + "\t\t" + temp.length();
            socOut.println(s[i]);
        }
        socOut.write("\r\n");
        socOut.flush();
    }

    /**
     * 更换目录
     * @throws IOException
     */
    private void changeDirectory() throws IOException {
        /**
         * 返回上级目录
         */
        if(str.equals("cd ..")){
            /**
             * 判断是否在服务器指定的根目录下
             */
            if(!pathName.equals("E:\\NetWork\\Test_Send")){
                pathName = file.getParent();
                file = new File(pathName);
                socOut.println("CourseEnv > OK");
                socOut.write("\r\n");
                socOut.flush();

            } else{
                socOut.println("already in root directory!");
                socOut.write("\r\n");
                socOut.flush();
            }

        }else{
            String newDir = pathName + File.separator + str;

            File canMoveTo = new File(newDir);
            /**
             * 处理只输入cd的情况，向客户端提示输入目录
             */
            if(str.equals("cd")){
                socOut.println("请输入目录");
                socOut.write("\r\n");
                socOut.flush();
                str = socIn.readLine();
                newDir = pathName + File.separator + str;
                System.out.println(newDir);
                canMoveTo = new File(newDir);
                if(canMoveTo.exists() && canMoveTo.isDirectory()){

                    pathName = newDir;
                    file = new File(newDir);
                    socOut.println(str+" > OK");
                }else{
                    socOut.println("Unknown Dir");
                }
                socOut.write("\r\n");
                socOut.flush();
            }
            /**
             * 正常处理更换目录
             */
            else {
                str = str.substring(3);
                newDir = pathName + File.separator + str;

                canMoveTo = new File(newDir);
                if(str.length() == 0){
                    socOut.println("请输入目录");
                    socOut.write("\r\n");
                    socOut.flush();
                    str = socIn.readLine();
                    newDir = pathName + File.separator + str;
                    canMoveTo = new File(newDir);
                }
                if(canMoveTo.exists() && canMoveTo.isDirectory()){

                    pathName = newDir;
                    file = new File(newDir);
                    socOut.println(str+" > OK");
                }else{
                    socOut.println("Unknown Dir");
                }

                socOut.write("\r\n");
                socOut.flush();
            }
        }
    }

    /**
     * 从服务器获取文件
     * @throws IOException
     * @throws InterruptedException
     */
    private void get() throws IOException, InterruptedException {
        File fileToSend = new File(pathName + File.separator + str.substring(4));
        if(fileToSend.isFile()){
            InputStream inputStream = new FileInputStream(pathName + File.separator + str.substring(4));
            byte[] buf = new byte[1024];
            //使用DatagramSocket连接并使用DatagramPacket包装传输数据报
            DatagramPacket dataPkt = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(),2021);
            DatagramSocket dataSkt = new DatagramSocket();

            socOut.println("开始传输文件:" + str.substring(4));
            socOut.println(fileToSend.length());
            socOut.write("\r\n");
            socOut.flush();
            if(socIn.readLine().equals("Ready To Receive")) {
                while(inputStream.read(buf) != -1){
                    dataSkt.send(dataPkt);
                    //每发一个数据包就暂停一下确保顺序发送
                    TimeUnit.MICROSECONDS.sleep(1);
                }
                System.out.println("传输完成");
                socIn.readLine();
            }
        }else{
            socOut.println("Invalid File");
            socOut.write("\r\n");
            socOut.flush();
        }
    }
}
