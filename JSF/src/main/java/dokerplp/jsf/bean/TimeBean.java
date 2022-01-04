package dokerplp.jsf.bean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Named("timeBean")
@SessionScoped
public class TimeBean implements Serializable {

    private static final long serialVersionUID = 1;

    private int day;
    private int month;
    private int year;

    private int hour;
    private int min;
    private int sec;

    public int getDay() {
        return ZonedDateTime.now().getDayOfMonth();
    }

    public int getMonth() {
        return ZonedDateTime.now().getMonthValue();
    }

    public int getYear() {
        return ZonedDateTime.now().getYear();
    }

    public int getHour() {
        return ZonedDateTime.now().getHour();
    }

    public int getMin() {
        return ZonedDateTime.now().getMinute();
    }

    public int getSec() {
        return ZonedDateTime.now().getSecond();
    }
}
