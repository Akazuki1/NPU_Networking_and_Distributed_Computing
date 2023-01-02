package Server;

import bean.Matter;
import bean.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author:Zhang_Haolin
 * @date:2022/11/5
 * @version:1.0.0
 * @description:
 */
@WebService(targetNamespace = "http://www.todolist.com", name = "todolist", portName = "port")
public class Server {

    int id = 0;

    private ArrayList<User> userList = new ArrayList<User>();
    private ArrayList<Matter> matterList = new ArrayList<Matter>();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
    public static void main(String[] args){
        Endpoint.publish("http://127.0.0.1:8001/webservice/todolist", new Server());
        System.out.println("Ready");
    }

    @WebMethod
    /**
     * 输入名字和密码注册
     */
    public boolean register(String name, String password){
        //检验是否已经注册过了
        if(isExist(name)){
            return false;
        }
        userList.add(new User(name, password));

        return true;
    }

    @WebMethod
    /**
     * 添加待办事务
     */
    public int add(String name, String password, String start, String end, String description) throws ParseException {
        User user = findUser(name);

        Date startTime = simpleDateFormat.parse(start);
        Date endTime = simpleDateFormat.parse(end);

        if((!isExist(name))||((!loginCheck(name, password)))){
            return -1;
        }
        if(isOccupied(user, startTime, endTime)){
            return -2;
        }

        Matter newMatter = new Matter(startTime, endTime, description);
        newMatter.setId(id++);
        matterList.add(newMatter);

        return 0;
    }

    @WebMethod
    /**
     * 查询事务
     */
    public List<String> query(String name, String password, String start, String end) throws ParseException {
        if(loginCheck(name, password)){
            Date startTime = simpleDateFormat.parse(start);
            Date endTime = simpleDateFormat.parse(end);
            ArrayList<Matter> tempList = new ArrayList<Matter>();
            for(Matter matter : matterList){
                if(matter.getStartTime().compareTo(startTime) >= 0 && matter.getEndTime().compareTo(endTime) <= 0){
                    tempList.add(matter);
                }
            }
            int n = tempList.size();
            sortList(tempList);
            List<String> s = new ArrayList<String>();
            //String[][] s = new String[n][5];
            for(int i = 0; i < tempList.size(); i++){
                s.add(String.valueOf(tempList.get(i).getId()));
                s.add(simpleDateFormat.format(tempList.get(i).getStartTime()));
                s.add(simpleDateFormat.format(tempList.get(i).getEndTime()));
                s.add(tempList.get(i).getDescription());
            }
            return s;
        } else return null;
    }

    @WebMethod
    /**
     * 删除事务
     */
    public boolean delete(String name, String password, int id){
        if(loginCheck(name, password)){
            User user = findUser(name);
            for(int i = 0; i < matterList.size(); i++){
                Matter temp = matterList.get(i);
                if(temp.getId() == id){
                    matterList.remove(matterList.get(i));
                    return true;
                }
            }
        }
        return false;
    }

    @WebMethod
    /**
     * 清空事务
     */
    public boolean clear(String name, String password) {
        if(loginCheck(name, password)){
            matterList.clear();
            return true;
        }
        return false;
    }

    /**
     * 查询给定名称的用户是否已经存在
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
     * 登录认证
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
     * 给定名字寻找用户
     * @param name
     * @return
     */
    public User findUser(String name){

        if(name != null){
            for(User user : userList){
                if(name.equals(user.getName())){
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * 查询用户给定时段内是否已经被待办事项占用
     * @param user
     * @param start
     * @param end
     * @return
     */
    public boolean isOccupied(User user, Date start, Date end){
        if(user.getMatterList().size() == 0){
            return false;
        }
        for(Matter matter : user.getMatterList()){
            if(later(matter.getStartTime(), start).compareTo(before(matter.getEndTime(), end))<=0){
                return true;
            }
        }
        return false;
    }

    /**
     * 返回时间晚的那个
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
     * 返回时间早的那个
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
     * 给事务按时间排序
     * @param tempList
     */
    private void sortList(ArrayList<Matter> tempList){
        for(int i = 0; i < tempList.size(); i++){
            for(int j = i; j < tempList.size(); j++){
                if(tempList.get(j).getStartTime().before(tempList.get(i).getStartTime())){
                    swap(tempList.get(i), tempList.get(j));
                }
            }
        }
    }

    /**
     * 交换两个事务
     * @param matter1
     * @param matter2
     */
    private void swap(Matter matter1, Matter matter2){

        Date startTime = matter1.getStartTime();
        matter1.setStartTime(matter2.getStartTime());
        matter2.setStartTime(startTime);

        Date endTime = matter1.getEndTime();
        matter1.setEndTime(matter2.getEndTime());
        matter2.setEndTime(endTime);

        String description = matter1.getDescription();
        matter1.setDescription(matter2.getDescription());
        matter2.setDescription(description);

        int id = matter1.getId();
        matter1.setId(matter2.getId());
        matter2.setId(id);
    }

}
