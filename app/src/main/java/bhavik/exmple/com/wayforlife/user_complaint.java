package bhavik.exmple.com.wayforlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_complaint extends AppCompatActivity {
    private EditText title, complaint;
    private Button post_complaint;
    DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_complaint);

        mdatabase= FirebaseDatabase.getInstance().getReference("complaints");

        title= findViewById(R.id.title);
        complaint= findViewById(R.id.complaint);
        post_complaint = findViewById(R.id.post_complaint);

        post_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addComplaint();
            }
        });

    }

    public void addComplaint(){
        String complaintTitle = title.getText().toString();
        String complaintComplaint = complaint.getText().toString();

        if(!TextUtils.isEmpty(complaintTitle) && !TextUtils.isEmpty(complaintComplaint)){
            complaints_model mcomplaints = new complaints_model(complaintTitle,complaintComplaint);
            mdatabase.push().setValue(mcomplaints);
            title.setText("");
            complaint.setText("");
        }
        else {
            Toast.makeText(this,"Please type the complaint or discription",Toast.LENGTH_LONG).show();
        }

    }
}
