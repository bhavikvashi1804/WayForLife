package bhavik.exmple.com.wayforlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class uploadEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_event);

        EditText ename=(EditText)findViewById(R.id.upload_event_ename);
        EditText eplace=(EditText)findViewById(R.id.upload_event_eplace);
        EditText eDesc=(EditText)findViewById(R.id.upload_event_eDesc);
        EditText eDate=(EditText)findViewById(R.id.upload_event_eDate);
        EditText eDur=(EditText)findViewById(R.id.upload_event_eDur);

        Button createEvent=(Button)findViewById(R.id.upload_event_create);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
}
