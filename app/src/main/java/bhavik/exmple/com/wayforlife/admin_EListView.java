package bhavik.exmple.com.wayforlife;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import bhavik.exmple.com.wayforlife.Database.EventsObj;

public class admin_EListView extends AppCompatActivity {


    private EventAdapter myAdapter;
    private ListView myListView;
    private ArrayList<EventsObj> myArrayList;
    ProgressDialog pd;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__elist_view);



        myArrayList=new ArrayList<EventsObj>();

        myListView=(ListView)findViewById(R.id.AdminEventList);
        myAdapter = new EventAdapter(this,myArrayList);


        myListView.setAdapter(myAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventsObj clickedObj= (EventsObj) parent.getItemAtPosition(position);
                Intent i=new Intent(getApplicationContext(),AdminOneEvent.class);
                i.putExtra("EID",clickedObj.getID());
                startActivity(i);
            }
        });




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
            pd = new ProgressDialog(admin_EListView.this);
            pd.show();
            pd.setMessage("Geting events...");

            //        pd.dismiss();

            DatabaseReference r=FirebaseDatabase.getInstance().getReference();

            r.child("EventInfo").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                    final String EID=dataSnapshot.getKey();
                    final String[] cEName = {""};
                    final String[] cEDate = { "" };
                    final String[] cEPlace = { "" };
                    final String[] cEDur = { "" };
                    final String[] cEDesc = { "" };

                    DatabaseReference r1=FirebaseDatabase.getInstance().getReference();
                    r1.child("EventInfo").child(EID).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {




                            if(dataSnapshot.getKey().equals("Event Name"))
                            {
                                cEName[0] =dataSnapshot.getValue(String.class);

                            }
                            if(dataSnapshot.getKey().equals("Event Date"))
                            {
                                cEDate[0] =dataSnapshot.getValue(String.class);
                            }
                            if(dataSnapshot.getKey().equals("Event Desc"))
                            {
                                cEDesc[0] =dataSnapshot.getValue(String.class);
                            }
                            if(dataSnapshot.getKey().equals("Event Dur"))
                            {
                                cEDur[0] =dataSnapshot.getValue(String.class);
                            }
                            if(dataSnapshot.getKey().equals("Event Place"))
                            {
                                cEPlace[0] =dataSnapshot.getValue(String.class);
                            }

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

                    final Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            myArrayList.add(new EventsObj(EID,cEName[0], cEDesc[0], cEPlace[0], cEDate[0], cEDur[0]));
                            myAdapter.notifyDataSetChanged();
                            pd.dismiss();
                        }
                    }, 5000);

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

            // temp added myArrayList.add(new EventsObj("Apple","Hello","Ankleshwar","12/1/2019","12:00","12H"));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_SHORT).show();
        }
    }
}



