package bean;

import java.util.Date;

/**
 * @author:Zhang_Haolin
 * @date:2022/11/2
 * @version:1.0.0
 * @description:
 */
public class Meeting {

    private User holder;
    private User participant;
    private Date startTime;
    private Date endTime;
    private String title;
    private int id;

    public Meeting(User holder, User participant, Date startTime, Date endTime, String title){
        this.holder = holder;
        this.participant = participant;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
    }

    public int getId(){return id;};

    public void setId(int id){
        this.id = id;
    }

    public User getHolder(){return holder;};

    public void setHolder(User user){this.holder = user;};

    public User getParticipant(){return participant;};

    public void setParticipant(User participant){this.participant = participant;};

    public Date getStartTime(){return startTime;};

    public void setStartTime(Date start){this.startTime = start;};

    public Date getEndTime(){return endTime;};

    public void setEndTime(Date end){this.endTime = end;};

    public String getTitle(){return title;};

    public void setTitle(String title){this.title = title;};

    public String toString(){
        return "[Holder: " + holder +", Participant: " + participant +
                ", Start: " + startTime + ", End: " + endTime + ", Title: "+title + "]";
    }
}
