package bhavik.exmple.com.wayforlife;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_add_poll extends AppCompatActivity {

    private EditText poll_question;
    private Button post_poll;
    DatabaseReference mdatabase;
    private EditText yes,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_poll);

        mdatabase= FirebaseDatabase.getInstance().getReference("polls");

        poll_question= findViewById(R.id.question);

        post_poll = findViewById(R.id.post_poll);

        post_poll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPoll();
            }
        });

    }

    public void addPoll(){
        String pollQuestion = poll_question.getText().toString();


        if(!TextUtils.isEmpty(pollQuestion)){
            admin_polls_model mpolls = new admin_polls_model(pollQuestion,0,0);
            mdatabase.push().setValue(mpolls);
            poll_question.setText("");

        }
        else {
            Toast.makeText(this,"Please type the poll",Toast.LENGTH_LONG).show();
        }

    }

}
