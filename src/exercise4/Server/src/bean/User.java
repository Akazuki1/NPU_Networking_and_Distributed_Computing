package bean;

import java.util.ArrayList;

/**
 * @author:Zhang_Haolin
 * @date:2022/11/5
 * @version:1.0.0
 * @description:
 */
public class User {

    private String name;
    private String password;
    private int numOfList;
    private ArrayList<Matter> matterList;

    public User(String name, String password){
        this.name = name;
        this.password = password;
        matterList = new ArrayList<>();
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

    public ArrayList<Matter> getMatterList(){return matterList;}

    public int getNumOfList(){return numOfList;}

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean addToDoList(Matter toDoList){
        numOfList++;
        return matterList.add(toDoList);
    }
}

