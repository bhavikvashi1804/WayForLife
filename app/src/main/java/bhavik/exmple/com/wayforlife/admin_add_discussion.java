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

public class admin_add_discussion extends AppCompatActivity {

    private EditText title_discussion;
    private EditText description_discussion;
    private Button discussion_post;
    DatabaseReference mdatabase;
    private EditText yes,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_discussion);

        mdatabase= FirebaseDatabase.getInstance().getReference("discussions");

        title_discussion= findViewById(R.id.discussion_title);
        description_discussion =findViewById(R.id.discussion_description);
        discussion_post = findViewById(R.id.post_discussion);

        discussion_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDiscussion();
            }
        });

    }

    public void addDiscussion(){
        String title = title_discussion.getText().toString();
        String discussion = description_discussion.getText().toString();

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(discussion) ){
            admin_discussions_model mdiscussions = new admin_discussions_model(title,discussion);
            mdatabase.push().setValue(mdiscussions);
            title_discussion.setText("");
            description_discussion.setText("");

        }
        else {
            Toast.makeText(this,"Please type the title and description",Toast.LENGTH_LONG).show();
        }

    }

}
