package bhavik.exmple.com.wayforlife;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityFollowUs extends AppCompatActivity {

    public void fb(String id){

        try {

            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("fb://page/"+id ));
            startActivity(intent);


        }catch (ActivityNotFoundException e){
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/"+id));
            startActivity(intent);

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_us);
    }

    public void followfacebook(View view)
    {
        fb("wayforlifeofficial");
    }

    public void followtwitter(View view)
    {
        Uri uri = Uri.parse("https://twitter.com/wayforlifeindia/"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void followlinkedin(View view)
    {
        Uri uri = Uri.parse("https://www.linkedin.com/company/wayforlife/"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void followlinstagram(View view)
    {
        Uri uri = Uri.parse("https://www.instagram.com/wayforlifeofficial/"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
