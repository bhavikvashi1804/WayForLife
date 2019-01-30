package bhavik.exmple.com.wayforlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class News extends AppCompatActivity {
    Button discussions, polls, complaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        discussions =findViewById(R.id.discussions);
        polls =findViewById(R.id.polls);
        complaints =findViewById(R.id.complaints);

        discussions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),discussions.class);
                startActivity(i);
            }
        });


        complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getApplicationContext(),complaints.class);
                startActivity(k);
            }
        });
        
        polls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j =new Intent(getApplicationContext(),polls.class);
                startActivity(j);
            }
        });

    }
}
