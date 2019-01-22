package bhavik.exmple.com.wayforlife;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class registerAdmin extends AppCompatActivity {

    ProgressDialog pd;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        final  EditText uname=(EditText)findViewById(R.id.admin_reg_uname);
        final  EditText upassword=(EditText)findViewById(R.id.admin_reg_upw);


        boolean connected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        Button reg=(Button)findViewById(R.id.adminreg);
        final boolean finalConnected = connected;
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(finalConnected) {

                    String username = uname.getText().toString();
                    String password = upassword.getText().toString();

                    if (username.isEmpty() && password.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please enter the username & password", Toast.LENGTH_SHORT).show();

                    } else {
                        pd = new ProgressDialog(registerAdmin.this);
                        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        pd.setTitle("Updating Database");
                        pd.setProgress(0);
                        pd.show();
                        DatabaseReference r = database.getInstance().getReference();
                        r.child("Admins").child(username).setValue(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {
                                    pd.dismiss();
                                    Toast.makeText(getApplicationContext(), "Database Updated", Toast.LENGTH_SHORT).show();

                                } else
                                    Toast.makeText(getApplicationContext(), "Database entry is not created", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
