package bhavik.exmple.com.wayforlife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import bhavik.exmple.com.wayforlife.Database.EventsObj;

public class EventAdapter extends ArrayAdapter<EventsObj>
{
    public EventAdapter(Context context, ArrayList<EventsObj> arrayList)
    {
        super(context,0,arrayList);

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        EventsObj curEvent=getItem(position);

        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.event_list, parent, false);
        }

        TextView ename=(TextView)convertView.findViewById(R.id.event_list_ename);
        TextView eplace=(TextView)convertView.findViewById(R.id.event_list_eplace);
        TextView edate=(TextView)convertView.findViewById(R.id.event_list_date);
        TextView edur=(TextView)convertView.findViewById(R.id.event_list_edur);

        ename.setText(curEvent.getName());
        eplace.setText(curEvent.getPlace());
        edate.setText(curEvent.getDate());
        edur.setText(curEvent.getDur());

        return convertView;
    }
}
