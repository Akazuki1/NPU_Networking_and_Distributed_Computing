package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import bean.Meeting;
import bean.User;
import rface.HelloInterface;

/**
 * 扩展UnicastRemoteObject类，并实现远程接口HelloInterface 
 * 
 * @author wben
 *
 */
public class Hello extends UnicastRemoteObject implements HelloInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status = "0000";
	private int id = 0;
	private List<User> userList = new ArrayList<User>();
	private List<Meeting> meetingList = new ArrayList<Meeting>();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
	
	/**
	 * 必须定义构造方法，即使是默认构造方法，也必须把它明确地写出来，因为它必须抛出出RemoteException异常
	 */
	public Hello() throws RemoteException {};

	/**
	 * 远程接口方法的实现
	 */
	public String echo(String msg) throws RemoteException {
		System.out.println("Called by HelloClient");
		status = status + msg;
		System.out.println(getStatus());
		return "[rmi echo]: " + msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	/**
	 * 根据name查找user
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	public User findUser(String name) throws RemoteException {
		for(User user : userList){
			if(user.getName().equals(name)){
				return user;
			}
		}
		return null;
	}

	@Override
	/**
	 * 输入name，password注册用户
	 * @param name
	 * @param password
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public boolean register(String name, String password) throws RemoteException {
		if(isExist(name)){
			return false;
		}
		userList.add(new User(name, password));

		return true;
	}

	@Override
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
	public int add(String name, String password, String participantName, String startTime, String endTime, String title) throws RemoteException, ParseException {
		User host = findUser(name);
		User participant = findUser(participantName);
		Date start = simpleDateFormat.parse(startTime);
		Date end = simpleDateFormat.parse(endTime);

		if((!isExist(name))||((!loginCheck(name, password)))){
			return -1;
		}
		if(name.equals(participantName)){
			return -2;
		}
		if(!isExist(participantName)){
			return -3;
		}
		if(isOccupied(host, start, end)){
			return -4;
		}
		if(isOccupied(participant, start, end)){
			return -5;
		}

		Meeting newMeeting = new Meeting(host, participant, start, end, title);
		newMeeting.setId(id++);
		meetingList.add(newMeeting);
		host.getMeetingList().add(newMeeting);
		participant.getMeetingList().add(newMeeting);
		return 0;
	}

	@Override
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
	public String[][] query(String name, String password, String start, String end) throws RemoteException, ParseException {
		if(loginCheck(name, password)){
			Date startTime = simpleDateFormat.parse(start);
			Date endTime = simpleDateFormat.parse(end);
			ArrayList<Meeting> tempList = new ArrayList<Meeting>();
			for(Meeting meeting : meetingList){
				if(meeting.getStartTime().compareTo(startTime) >= 0 && meeting.getEndTime().compareTo(endTime) <= 0){
					tempList.add(meeting);
				}
			}
			int n = tempList.size();
			sortList(tempList);
			String[][] s = new String[n][5];
			for(int i = 0; i < tempList.size(); i++){
				s[i][0] = String.valueOf(tempList.get(i).getId());
				s[i][1] = tempList.get(i).getTitle();
				s[i][2] = tempList.get(i).getHolder().getName() + "," + tempList.get(i).getParticipant().getName();
				s[i][3] = simpleDateFormat.format(tempList.get(i).getStartTime());
				s[i][4] = simpleDateFormat.format(tempList.get(i).getEndTime());
			}
			return s;
		} else return null;
	}

	@Override
	/**
	 * 删除会议
	 * @param name
	 * @param password
	 * @param id
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public boolean delete(String name, String password, int id) throws RemoteException {
		if(loginCheck(name, password)){
			User user = findUser(name);
			for(int i = 0; i < meetingList.size(); i++){
				Meeting temp = meetingList.get(i);
				if(temp.getHolder().equals(user)&&temp.getId() == id){
					temp.getHolder().getMeetingList().remove(meetingList.get(i));
					temp.getParticipant().getMeetingList().remove(meetingList.get(i));
					meetingList.remove(meetingList.get(i));
					return true;
				}
			}
		}
		return false;
	}

	@Override
	/**
	 * 清除用户所有会议
	 * @param name
	 * @param password
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public boolean clear(String name, String password) throws RemoteException {
		if(loginCheck(name, password)){
			User user = findUser(name);
			List<Meeting> tempList = new ArrayList<Meeting>();
			for(Meeting meeting : meetingList){
				if(meeting.getHolder().equals(user)){
					tempList.add(meeting);
				}
			}
			for(Meeting meeting : tempList){
				User holder = findUser(name);
				User participant = findUser(meeting.getParticipant().getName());
				holder.getMeetingList().remove(meeting);
				participant.getMeetingList().remove(meeting);
				meetingList.remove(meeting);

			}
			return true;
		}
		return false;
	}


	/**
	 * 判断该用户是否存在
	 * @param name
	 * @return
	 */
	public boolean isExist(String name){
		for(User user : userList){
			if(user.getName().equals(name)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 登陆检查，检查用户名是否匹配注册时输入的密码
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean loginCheck(String name, String password){
		for(User user : userList){
			if(user.getName().equals(name)&&user.getPassword().equals(password)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 该用户是否在该阶段有会议
	 * @param user
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean isOccupied(User user, Date start, Date end){
		if(user.getMeetingList().size() == 0){
			return false;
		}
		for(Meeting meeting : user.getMeetingList()){
			if(later(meeting.getStartTime(), start).compareTo(before(meeting.getEndTime(), end))<=0){
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回两个时间更晚的那个
	 * @param time1
	 * @param time2
	 * @return
	 */
	private Date later(Date time1, Date time2){
		if(time1.after(time2)){
			return time1;
		} else return time2;
	}

	/**
	 * 返回两个时间更早的那个
	 * @param time1
	 * @param time2
	 * @return
	 */
	private Date before(Date time1, Date time2){
		if(time1.before(time2)){
			return time1;
		} else return time2;
	}

	/**
	 * 针对开始时间进行分类
	 * @param tempList
	 */
	private void sortList(ArrayList<Meeting> tempList){
		for(int i = 0; i < tempList.size(); i++){
			for(int j = i; j < tempList.size(); j++){
				if(tempList.get(j).getStartTime().before(tempList.get(i).getStartTime())){
					swap(tempList.get(i), tempList.get(j));
				}
			}
		}
	}

	/**
	 * 交换两个会议对象
	 * @param meeting1
	 * @param meeting2
	 */
	private void swap(Meeting meeting1, Meeting meeting2){
		User holder = meeting1.getHolder();
		meeting1.setHolder(meeting2.getHolder());
		meeting2.setHolder(holder);

		User participant = meeting1.getParticipant();
		meeting1.setParticipant(meeting2.getParticipant());
		meeting2.setParticipant(participant);

		Date startTime = meeting1.getStartTime();
		meeting1.setStartTime(meeting2.getStartTime());
		meeting2.setStartTime(startTime);

		Date endTime = meeting1.getEndTime();
		meeting1.setEndTime(meeting2.getEndTime());
		meeting2.setEndTime(endTime);

		String title = meeting1.getTitle();
		meeting1.setTitle(meeting2.getTitle());
		meeting2.setTitle(title);

		int id = meeting1.getId();
		meeting1.setId(meeting2.getId());
		meeting2.setId(id);
	}
}
