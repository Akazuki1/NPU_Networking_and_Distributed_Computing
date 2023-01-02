package bean;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author:Zhang_Haolin
 * @date:2022/11/5
 * @version:1.0.0
 * @description:
 */

/**
 * 待做事项表
 */
public class Matter {

    private Date startTime;
    private Date endTime;
    private String description;
    private int id;

    public Matter(Date startTime, Date endTime, String description){

        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Matter{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
