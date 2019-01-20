package bhavik.exmple.com.wayforlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class admin_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        EditText uname= (EditText)findViewById(R.id.admin_loging_uname);
        EditText upassword=(EditText)findViewById(R.id.admin_loging_upw);

        Button loging=(Button)findViewById(R.id.adminloging_loging);
        loging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loging logic

                Intent i=new Intent(getApplicationContext(),uploadEvent.class);
                startActivity(i);
            }
        });

        Button register=(Button)findViewById(R.id.adminloging_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send email
            }
        });
    }

}
