package bean;

import java.util.Date;

/**
 * @author:Zhang_Haolin
 * @date:2022/11/12
 * @version:1.0.0
 * @description:
 */
public class Message {

    private User sender;
    private User receiver;
    private String text;
    private Date date;
    //private int id;

    public Message(User receiver, String text, Date date){
        //this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.date = date;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /*
    public void setId(int id){

        this.id = id;
    }
    */

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
