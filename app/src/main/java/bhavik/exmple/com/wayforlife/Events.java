package bhavik.exmple.com.wayforlife;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import bhavik.exmple.com.wayforlife.Database.EventsObj;

public class Events extends AppCompatActivity {


    private EventAdapter myAdapter;
    private ListView myListView;
    private ArrayList<EventsObj> myArrayList;
    ProgressDialog pd;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        myArrayList=new ArrayList<EventsObj>();

        myListView=(ListView)findViewById(R.id.eventlist);
        myAdapter = new EventAdapter(getApplicationContext(),myArrayList);
        myListView.setAdapter(myAdapter);


        boolean connected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;



        if(connected)
        {
            pd = new ProgressDialog(Events.this);
            pd.show();
            pd.setMessage("Geting events...");


            myArrayList=new ArrayList<EventsObj>();



            final Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pd.dismiss();



                    DatabaseReference r=FirebaseDatabase.getInstance().getReference();



                    r.child("EventInfo").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            final EventsObj obj1=new EventsObj();
                            final String EID=dataSnapshot.getKey();


                            DatabaseReference r1=FirebaseDatabase.getInstance().getReference();
                            r1.child("EventInfo").child(EID).addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    /*


                                    if(dataSnapshot.getKey().equals("Event Date"))
                                    {
                                        obj1.setEdate(dataSnapshot.getValue(String.class));
                                    }
                                    else if(dataSnapshot.getKey().equals("Event Name"))
                                    {
                                        obj1.setName(dataSnapshot.getValue(String.class));
                                    }
                                    else if(dataSnapshot.getKey().equals("Event Dur"))
                                    {
                                        obj1.setDur(dataSnapshot.getValue(String.class));
                                    }
                                    else if(dataSnapshot.getKey().equals("Event Place"))
                                    {
                                        obj1.setPlace(dataSnapshot.getValue(String.class));
                                    }
                                    else if(dataSnapshot.getKey().equals("Event Desc"))
                                    {
                                        obj1.setDesc(dataSnapshot.getValue(String.class));
                                    }

                                    */
                                    //Log.d("My",)

                                    //myArrayList.add(obj1);
                                    //myAdapter.notifyDataSetChanged();

                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                }

                                @Override
                                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }
            }, 7000);




            // temp added myArrayList.add(new EventsObj("Apple","Hello","Ankleshwar","12/1/2019","12:00","12H"));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_SHORT).show();
        }





    }



}
