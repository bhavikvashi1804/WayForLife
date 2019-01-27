package bhavik.exmple.com.wayforlife;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdminDeleteEvent extends AppCompatActivity {


    ProgressDialog pd;
    FirebaseDatabase database;
    String name,date,dur,desc,place;
    private EditText ETname,ETdate,ETdur,ETdesc,ETplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_event);

        ETname=(EditText)findViewById(R.id.upload_event_ename);
        ETdate=(EditText)findViewById(R.id.upload_event_eDate);
        ETdur=(EditText)findViewById(R.id.upload_event_eDur);
        ETdesc=(EditText)findViewById(R.id.upload_event_eDesc);
        ETplace=(EditText)findViewById(R.id.upload_event_eplace);

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


        pd = new ProgressDialog(AdminDeleteEvent.this);
        pd.show();
        pd.setMessage("Fetching event information...");



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
                                ETname.setText(name);
                                ETdate.setText(date);
                                ETdur.setText(dur);
                                ETplace.setText(place);
                                ETdesc.setText(desc);

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


        Button update=(Button)findViewById(R.id.admin_update_event);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ename1=ETname.getText().toString();
                String eplace1=ETplace.getText().toString();
                String edesc1=ETdesc.getText().toString();
                String edate=ETdate.getText().toString();
                String edur=ETdur.getText().toString();

                // Write a message to the database






                String EventID=newString;


                if(ename1.isEmpty() && edesc1.isEmpty() && eplace1.isEmpty()  && edate.isEmpty() &&  edur.isEmpty()) {

                    Toast.makeText(getApplicationContext(),"Please fill all the information",Toast.LENGTH_SHORT).show();


                }

                else {

                    pd=new ProgressDialog(AdminDeleteEvent.this);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setTitle("Updating Database");
                    pd.setProgress(0);
                    pd.show();


                    DatabaseReference r = database.getInstance().getReference();
                    r.child("EventInfo").child(EventID).child("Event Name").setValue(ename1);
                    r.child("EventInfo").child(EventID).child("Event Place").setValue(eplace1);
                    r.child("EventInfo").child(EventID).child("Event Desc").setValue(edesc1);
                    r.child("EventInfo").child(EventID).child("Event Date").setValue(edate);
                    r.child("EventInfo").child(EventID).child("Event Dur").setValue(edur).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "Database Updated", Toast.LENGTH_SHORT).show();

                            }else
                                Toast.makeText(getApplicationContext(), "Database entry is not created", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });


    }
}
