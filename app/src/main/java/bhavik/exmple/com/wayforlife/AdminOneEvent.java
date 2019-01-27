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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminOneEvent extends AppCompatActivity {

    ProgressDialog pd;
    FirebaseDatabase database;
    String name,date,dur,desc,place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_one_event);



        pd = new ProgressDialog(AdminOneEvent.this);
        pd.show();
        pd.setMessage("Fetching event information...");

        final String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("EID");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("EID");
        }

        Log.d("My",newString);
        final TextView eName=(TextView)findViewById(R.id.displayOne_ename);
        final TextView eDate=(TextView)findViewById(R.id.displayOne_edate);
        final TextView ePlace=(TextView)findViewById(R.id.displayOne_eplace);
        final TextView eDur=(TextView)findViewById(R.id.displayOne_edur);
        final TextView eDesc=(TextView)findViewById(R.id.displayOne_edesc);


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
            DatabaseReference r=FirebaseDatabase.getInstance().getReference();

            r.child("EventInfo").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                    final String EID=dataSnapshot.getKey();
                    if(EID.equals(newString))
                    {
                        DatabaseReference r1=FirebaseDatabase.getInstance().getReference();
                        r1.child("EventInfo").child(EID).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {




                                if(dataSnapshot.getKey().equals("Event Name"))
                                {
                                    name=dataSnapshot.getValue(String.class);

                                }
                                if(dataSnapshot.getKey().equals("Event Date"))
                                {
                                    date =dataSnapshot.getValue(String.class);
                                }
                                if(dataSnapshot.getKey().equals("Event Desc"))
                                {
                                    desc =dataSnapshot.getValue(String.class);
                                }
                                if(dataSnapshot.getKey().equals("Event Dur"))
                                {
                                    dur =dataSnapshot.getValue(String.class);
                                }
                                if(dataSnapshot.getKey().equals("Event Place"))
                                {
                                    place =dataSnapshot.getValue(String.class);
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

                                pd.dismiss();
                                eName.setText(name);
                                eDate.setText(date);
                                eDur.setText(dur);
                                ePlace.setText(place);
                                eDesc.setText(desc);

                                // myArrayList.add(new EventsObj(EID,cEName[0], cEDesc[0], cEPlace[0], cEDate[0], cEDur[0]));
                                // myAdapter.notifyDataSetChanged();
                                pd.dismiss();
                            }
                        }, 5000);
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

        }
        else
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_SHORT).show();


        Button update=(Button)findViewById(R.id.admin_event_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),AdminDeleteEvent.class);
                i.putExtra("EID",newString);
                startActivity(i);
            }
        });


        Button delete=(Button)findViewById(R.id.admin_event_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference r=FirebaseDatabase.getInstance().getReference();

                r.child("EventInfo").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        final String EID=dataSnapshot.getKey();
                        if(EID.equals(newString))
                        {
                            r.child("EventInfo").child(newString).removeValue();
                            Toast.makeText(getApplicationContext(),"Event Deleted",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

    }
}
