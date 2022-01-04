package dokerplp.jsf.bean;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "responses")
@Named("responseBean")
@SessionScoped
@Getter
@Setter
public class ResponseBean implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "next_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "x")
    private Float x = 0f;

    @Column(name = "y")
    private Float y;

    @Column(name = "r")
    private Float r;

    @Column(name = "curtime")
    @CreationTimestamp
    private Timestamp curTime;

    @Column(name = "extime")
    private Long exTime;

    @Column(name = "res")
    private Boolean res;

    @Column(name = "left")
//    @Column(name = "left_t")
    private Float left;

    @Column(name = "top")
    private Float top;


    @Override
    public String toString() {
        return "ResponseBean{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", curTime=" + curTime +
                ", exTime=" + exTime +
                ", res=" + res +
                ", left=" + left +
                ", top=" + top +
                '}';
    }

    public String formatTime(){
        if (curTime == null) return "null";
        else return curTime.toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
