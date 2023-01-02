package rinterface;

import bean.Message;
import bean.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author:Zhang_Haolin
 * @date:2022/11/12
 * @version:1.0.0
 * @description:
 */
public interface MessageInterface extends Remote {

    /**
     * 远程接口方法必须抛出 java.rmi.RemoteException
     */

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     * @throws RemoteException
     */
    public boolean register(String username, String password) throws RemoteException;

    /**
     * 列出所有用户
     * @return
     * @throws RemoteException
     */
    public List<String> showUsers() throws RemoteException;

    /**
     * 列出所有收信
     * @param username
     * @param password
     * @return
     * @throws RemoteException
     */
    public String[][] checkMessage(String username, String password) throws RemoteException;

    /**
     * 给某人留信息
     * @param username
     * @param password
     * @param received
     * @param text
     * @return
     * @throws RemoteException
     */
    public int leaveMessage(String username, String password, String received, String text) throws RemoteException;
}
