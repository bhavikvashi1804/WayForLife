package bhavik.exmple.com.wayforlife.Database;

import java.sql.Time;
import java.util.Date;

public class EventsObj
{
    String ID,name,desc,place,edate,dur;

    public EventsObj(){

    }

    public EventsObj(String ID,String name1,String desc1,String place1,String edate1,String dur1)
    {
        this.ID=ID;
        name=name1;
        desc=desc1;
        place=place1;
        edate=edate1;
        dur=dur1;

    }

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

    public String getPlace() {
        return place;
    }

    public String getDesc() {
        return desc;
    }

    public String getDur() {
        return dur;
    }

    public String getDate() {
        return edate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
}
