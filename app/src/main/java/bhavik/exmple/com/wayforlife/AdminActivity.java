package bhavik.exmple.com.wayforlife;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

public class AdminActivity extends AppCompatActivity {


    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        Button upload=(Button)findViewById(R.id.activity_admin_uploade);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),uploadEvent.class);
                startActivity(i);
            }
        });

        Button update=(Button)findViewById(R.id.activity_admin_updatee);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_EListView.class);
                startActivity(i);

            }
        });

        Button register=(Button)findViewById(R.id.adminloging_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),registerAdmin.class);
                startActivity(i);
            }
        });
        
         Button discussion =(Button) findViewById(R.id.add_discussion);
        discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_add_discussion.class);
                startActivity(i);
            }
        });

        Button poll =(Button) findViewById(R.id.add_poll);
        poll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),admin_add_poll.class);
                startActivity(i);
            }
        });


    }

}
