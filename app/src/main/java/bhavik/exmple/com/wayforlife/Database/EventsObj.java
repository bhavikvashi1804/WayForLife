package bhavik.exmple.com.wayforlife.Database;

import java.sql.Time;
import java.util.Date;

public class EventsObj
{
    String name,desc,place,edate,etime,dur;
    
    public void setName(String name) {
        this.name = name;
    }
    public  void setPlace(String x)
    {
        place=x;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDur(String dur) {
        this.dur = dur;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getPlace() {
        return place;
    }

    public String getDesc() {
        return desc;
    }

    public String getDur() {
        return dur;
    }

    public String getEdate() {
        return edate;
    }

    public String getEtime() {
        return etime;
    }

    public String getName() {
        return name;
    }
}
