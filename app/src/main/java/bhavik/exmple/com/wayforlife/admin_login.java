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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_login extends AppCompatActivity {
    FirebaseDatabase database;
    ProgressDialog pd;
    Boolean check=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        final EditText uname= (EditText)findViewById(R.id.admin_loging_uname);
        final EditText upassword=(EditText)findViewById(R.id.admin_loging_upw);


        boolean connected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;







            Button loging = (Button) findViewById(R.id.adminloging_loging);
        final boolean finalConnected = connected;
        loging.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(finalConnected)
                    {


                        pd = new ProgressDialog(admin_login.this);
                        pd.show();
                        pd.setMessage("Authenticating...");

                        final String cUserName = uname.getText().toString();
                        final String cPassWord = upassword.getText().toString();

                        fetch(cUserName, cPassWord);

                        final Handler h = new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pd.dismiss();
                                if (check) {
                                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                    startActivity(intent);
                                    check = false;
                                } else {
                                    Toast.makeText(getApplicationContext(), "Username/ password doesnot match", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 5000);
                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

                    }



                }
            });
        /*
        else
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();
        */
    }

    private void fetch(String cUserName,String cPassWord)
    {
        final String name=cUserName,pw=cPassWord;

        DatabaseReference r=FirebaseDatabase.getInstance().getReference();


        r.child("Admins").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(name.equals(dataSnapshot.getKey()) && pw.equals(dataSnapshot.getValue(String.class)))
                {
                    check=true;
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
}
