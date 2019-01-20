package bhavik.exmple.com.wayforlife;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class uploadEvent extends AppCompatActivity {

    FirebaseDatabase database;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_event);
        pd=new ProgressDialog(getApplicationContext());

        final EditText ename=(EditText)findViewById(R.id.upload_event_ename);
        final EditText eplace=(EditText)findViewById(R.id.upload_event_eplace);
        final EditText eDesc=(EditText)findViewById(R.id.upload_event_eDesc);
        final EditText eDate=(EditText)findViewById(R.id.upload_event_eDate);
        final EditText eDur=(EditText)findViewById(R.id.upload_event_eDur);




        Button createEvent=(Button)findViewById(R.id.upload_event_create);

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String ename1=ename.getText().toString();
                String eplace1=eplace.getText().toString();
                String edesc1=eDesc.getText().toString();
                String edate=eDate.getText().toString();
                String edur=eDur.getText().toString();

                // Write a message to the database


                Date currentTime = Calendar.getInstance().getTime();
                String fDate = new SimpleDateFormat("yyyy-MM-dd").format(currentTime);
                String cTime=new SimpleDateFormat("h:mm a").format(currentTime);



                String EventID=fDate+cTime;


                if(ename1.isEmpty() && edesc1.isEmpty() && eplace1.isEmpty()  && edate.isEmpty() &&  edur.isEmpty()) {

                    Toast.makeText(getApplicationContext(),"Please fill all the information",Toast.LENGTH_SHORT).show();


                }

                else {

                    pd=new ProgressDialog(uploadEvent.this);
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
