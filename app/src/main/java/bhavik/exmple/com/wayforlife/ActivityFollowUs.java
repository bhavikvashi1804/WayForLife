package bhavik.exmple.com.wayforlife;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityFollowUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_us);
    }

    public void followfacebook(View view)
    {
        Uri uri = Uri.parse("https://m.facebook.com/wayforlifeofficial/"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
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
