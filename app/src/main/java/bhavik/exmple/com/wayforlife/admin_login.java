package bhavik.exmple.com.wayforlife;

import android.app.ProgressDialog;
import android.content.Intent;
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

        Button loging=(Button)findViewById(R.id.adminloging_loging);
        loging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd=new ProgressDialog(admin_login.this);
                pd.show();
                pd.setMessage("Authenticating...");

                final String cUserName=uname.getText().toString();
                final String cPassWord=upassword.getText().toString();


                fetch(cUserName,cPassWord);



                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(check)
                {
                    Intent intent=new Intent(getApplicationContext(),uploadEvent.class);
                    startActivity(intent);
                    pd.dismiss();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Username/ password doesnot match",Toast.LENGTH_SHORT).show();
                }




            }
        });

        Button register=(Button)findViewById(R.id.adminloging_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=uname.getText().toString();
                String password=upassword.getText().toString();

                if(username.isEmpty() && password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please enter the username & password",Toast.LENGTH_SHORT).show();

                }
                else
                {

                    pd=new ProgressDialog(admin_login.this);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setTitle("Updating Database");
                    pd.setProgress(0);
                    pd.show();

                    DatabaseReference r = database.getInstance().getReference();

                    r.child("Admins").child(username).setValue(password).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                //send email
            }
        });
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
