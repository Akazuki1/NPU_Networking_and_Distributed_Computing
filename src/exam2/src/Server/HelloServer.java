package Server;

import rinterface.MessageInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author:Zhang_Haolin
 * @date:2022/11/12
 * @version:1.0.0
 * @description:
 */
public class HelloServer {

    /**
     * 启动 RMI 注册服务并进行对象注册
     */
    public static void  main(String[] args) throws RemoteException {
        try{
            // 启动RMI注册服务，指定端口为1099　（1099为默认端口）
            LocateRegistry.createRegistry(1099);
            // 创建远程对象的一个或多个实例，下面是message对象
            // 可以用不同名字注册不同的实例
            MessageInterface message = new Hello();
            // 把hello注册到RMI注册服务器上，命名为message
            Naming.rebind("message", message);
            System.out.println("服务器准备就绪");

        } catch (RemoteException | MalformedURLException e) {
            //e.printStackTrace();
            System.out.println("服务器创建失败" + e);
        }

    }
}
