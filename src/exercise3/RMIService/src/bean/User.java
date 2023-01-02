package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/**
 * @author:Zhang_Haolin
 * @date:2022/11/2
 * @version:1.0.0
 * @description:
 */
public class User implements Serializable{
	private String name;
	private String password;
	private ArrayList<Meeting> meetingList;
	
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
		this.meetingList = new ArrayList<Meeting>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Meeting> getMeetingList(){return meetingList;};

	public String toString(){
		return "[name: "+name+",password:"+password+"]";
		
	}
}
