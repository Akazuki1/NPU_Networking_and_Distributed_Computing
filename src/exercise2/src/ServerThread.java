import java.io.*;
import java.net.Socket;
import java.util.Date;
/**
 * @author:Zhang_Haolin
 * @date:2022/10/21
 * @version:1.0.0
 * @description:
 */

public class ServerThread extends Thread{

    private BufferedOutputStream ostream = null;
    private BufferedInputStream istream = null;
    private Socket socket;
    private String PATH;
    private final String CRLF = "\r\n";
    private byte[] buffer = null;
    private String msg;
    private String header;
    private String[] splitedHeader;
    private BufferedReader bufferedReader;


    StringBuilder request = new StringBuilder();
    StringBuilder response = new StringBuilder();


    /**
     * 创建线程实例
     * @param socket
     * @param path
     */
    public ServerThread(Socket socket, String path){
        this.socket = socket;
        this.PATH = path;
    }

    /**
     * 初始化线程实例
     * @throws IOException
     */
    public void init() throws IOException {
        try{
            istream = new BufferedInputStream(socket.getInputStream());
            ostream = new BufferedOutputStream(socket.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 重写run方法
     */
    @Override
    public void run(){
        try{
            init();
            System.out.println("new connection established:"+socket.getInetAddress()+":"+socket.getPort());
            //GET /face.jpg HTTP/1.1
            //PUT /face.jpg http/1.1
            request();
            splitedHeader = header.split(" ");
            /**
             * 检测请求头是否符合协议内容 如果不符合则返回400 Bad Request
             */
            if((splitedHeader.length == 3)&&(splitedHeader[2].startsWith("HTTP"))){
                if(splitedHeader[0].equals("GET")){
                    get();
                } else if(splitedHeader[0].equals("PUT")){
                    put();
                } else {
                    badRequest();
                }
            } else{
                badRequest();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 读取请求并提取报文头
     * @throws IOException
     */
    private void request() throws IOException {

        int c = 0, last = 0;
        boolean inHeader = true;
        boolean flag = false;
        String mark = "";
        while(inHeader && (c = istream.read()) != -1) {
            switch (c){
                case '\r':
                    break;
                case  '\n':
                    if(c==last){
                        inHeader = false;
                        break;
                    }
                    last = c;
                    request.append(mark+"\n");
                    if(!flag){//如果第一行读完了，把第一行赋值给header，header负责后续处理
                        header = mark;
                    }
                    mark = "";
                    flag = true;
                    break;
                default:
                    last = c;
                    mark += (char) c;
            }
        }
        System.out.println("new HTTP request from [" + socket.getInetAddress()+":"+socket.getPort()+"]" + CRLF);
        System.out.println(request);
    }

    /**
     * GET方法的处理
     * @throws IOException
     */
    private void get() throws IOException{

        if(splitedHeader[1].endsWith(".htm") || splitedHeader[1].endsWith(".html")){
            File file =new File(PATH + File.separator + splitedHeader[1]);
            if(file.exists()){
                sendHtml(file);
            } else {
                notFound();
            }
        } else if(splitedHeader[1].endsWith(".jpg")) {
            File file = new File(PATH + File.separator + splitedHeader[1]);
            if(file.exists()){
                sendJPG(file);
            } else {
                notFound();
            }
        }
    }

    /**
     * PUT方法的处理
     */
    private void put(){

        try{

            response.append("HTTP/1.0 200 OK" + CRLF);
            response.append("Date: " + new Date().toString() + CRLF);
            response.append("Content-Type: text/html;charset=ISO-8819-1" + CRLF);
            response.append(CRLF);

            String path = PATH +File.separator + "receive" + File.separator + splitedHeader[1];
            File saveFile = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            int c = 0;

            while((istream.available()>0) &&((c = istream.read())!= -1)){
                fileOutputStream.write(c);
                fileOutputStream.flush();
            }
            fileOutputStream.flush();
            fileOutputStream.close();

            msg = response.toString();
            buffer = msg.getBytes();
            ostream.write(buffer, 0, buffer.length);
            ostream.flush();
            ostream.close();
            istream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送html文件
     * @param file
     */
    private void sendHtml(File file){
        StringBuilder html = new StringBuilder();
        try{
            bufferedReader = new BufferedReader(new FileReader(file.getPath()));
            String line = bufferedReader.readLine();
            while(line != null){
                html.append(line);
                html.append("\n");
                line = bufferedReader.readLine();
            }

            response.append("HTTP/1.0 200 OK" + CRLF);
            response.append("Date: " + new Date().toString() + CRLF);
            response.append("Content-Type: text/html;charset=ISO-8859-1" + CRLF);
            response.append("Content-Length: " + file.length() + CRLF);
            response.append(CRLF);
            response.append(html);

            msg = response.toString();
            buffer = msg.getBytes();
            ostream.write(buffer, 0, buffer.length);
            ostream.flush();
            ostream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送jpg格式的图片
     * @param file
     */
    private void sendJPG(File file){

        try{
            int length = 0;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] fileBuffer = new byte[8192];

            response.append("HTTP/1.0 200 OK" + CRLF);
            response.append("Date: " + new Date().toString() + CRLF);
            response.append("Content-Type: image/jpeg;charset=ISO-8859-1" + CRLF);
            response.append("Content-Length: " + file.length() + CRLF + CRLF);
            msg = response.toString();
            buffer = msg.getBytes();
            ostream.write(buffer, 0, buffer.length);
            while((length = bufferedInputStream.read(fileBuffer)) != -1){
                ostream.write(fileBuffer, 0, length);
                ostream.flush();
            }
            ostream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 400BadRequest响应
     */
    private void badRequest(){

        StringBuilder html = new StringBuilder();
        File file = new File(PATH + File.separator + "send"+ File.separator + "400BadRequest.html");

        try{
            bufferedReader = new BufferedReader(new FileReader(file.getPath()));
            String line = bufferedReader.readLine();
            while(line != null){
                html.append(line);
                html.append("\n");
                line = bufferedReader.readLine();
            }
            response.append("HTTP/1.0 400 BadRequest" + CRLF);
            response.append("Date: " + new Date().toString() + CRLF);
            response.append("Content-Type: text/html;charset=ISO-8819-1" + CRLF );
            response.append("Content-Length: " + file.length() + CRLF);
            response.append(CRLF);
            response.append(html);

            msg = response.toString();
            buffer = msg.getBytes();
            ostream.write(buffer, 0, buffer.length);
            ostream.flush();
            ostream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 404NotFound响应
     */
    private void notFound() {

        StringBuilder html = new StringBuilder();
        File file = new File(PATH + File.separator + "send" + File.separator + "404NotFound.html");
        try{
            bufferedReader = new BufferedReader(new FileReader(file.getPath()));
            String line = bufferedReader.readLine();
            while(line != null){
                html.append(line);
                html.append("\n");
                line = bufferedReader.readLine();
            }
            response.append("HTTP/1.0 404 Not Found" + CRLF);
            response.append("Date: " + new Date().toString() + CRLF);
            response.append("Content-Type: text/html;charset=ISO-8859-1" + CRLF);
            response.append("Content-Length: " + file.length() + CRLF);
            response.append(CRLF);
            response.append(html);

            msg = response.toString();
            buffer = msg.getBytes();
            ostream.write(buffer, 0, buffer.length);
            ostream.flush();
            ostream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
