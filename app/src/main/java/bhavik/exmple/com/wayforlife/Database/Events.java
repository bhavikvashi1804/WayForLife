package bhavik.exmple.com.wayforlife.Database;

import java.sql.Time;
import java.util.Date;

public class Events
{
    String name,desc,place;
    Date edate;
    Time etime,dur;



    public void setDur(Time dur) {
        this.dur = dur;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    public void setPlace(String x)
    {
        place=x;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }
    public void setEtime(Time e)
    {
        etime=e;
    }

    public Date getEdate() {
        return edate;
    }

    public Time getEtime()
    {
        return etime;
    }
    public Time getDur()
    {
        return dur;
    }

    public String getDesc() {
        return desc;
    }
    public String getName(){
        return name;
    }

    public String getPlace() {
        return place;
    }
}
