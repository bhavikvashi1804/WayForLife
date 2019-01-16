package bhavik.exmple.com.wayforlife;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Report extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        final EditText topic = (EditText)findViewById(R.id.topic);
        final EditText body = (EditText)findViewById(R.id.body);
        Button button = (Button) findViewById(R.id.send);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic1 = topic.getText().toString();
                String body1 = body.getText().toString();
                String[] TO = {"contact@wayforlife.org"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_EMAIL,TO );

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, topic1);
                emailIntent.putExtra(Intent.EXTRA_TEXT, body1);

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();

                } catch (android.content.ActivityNotFoundException ex) {
                    Log.i("Msg","ERROR");
                }

            }
        });


    }
}
