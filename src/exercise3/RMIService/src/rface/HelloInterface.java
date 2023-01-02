package rface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

import bean.Meeting;
import bean.User;

/**
 * 远程接口必须扩展接口java.rmi.Remote 
 * 
 * @author wben
 *
 */
public interface HelloInterface extends Remote {

	
		/**  
	    * 远程接口方法必须抛出 java.rmi.RemoteException  
	    */

	/**
	 *
	 * @param msg
	 * @return
	 * @throws RemoteException
	 */
	public String echo(String msg) throws RemoteException;

	/**
	 * 根据name查找user
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	   public User findUser(String name) throws RemoteException;

	/**
	 * 输入name，password注册用户
	 * @param name
	 * @param password
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	   public boolean register(String name, String password) throws  java.rmi.RemoteException;

	/**
	 * 添加会议
	 * @param name
	 * @param password
	 * @param participantName
	 * @param startTime
	 * @param endTime
	 * @param title
	 * @return
	 * @throws java.rmi.RemoteException
	 * @throws ParseException
	 */
	   public int add(String name, String password, String participantName, String startTime, String endTime, String title) throws java.rmi.RemoteException, ParseException;

	/**
	 * 查找会议
	 * @param name
	 * @param password
	 * @param start
	 * @param end
	 * @return
	 * @throws java.rmi.RemoteException
	 * @throws ParseException
	 */
	   public String[][] query(String name, String password, String start, String end) throws java.rmi.RemoteException, ParseException;

	/**
	 * 删除会议
	 * @param name
	 * @param password
	 * @param id
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	   public boolean delete(String name, String password, int id) throws java.rmi.RemoteException;

	/**
	 * 清除用户所有会议
	 * @param name
	 * @param password
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	   public boolean clear(String name, String password) throws java.rmi.RemoteException;

}
