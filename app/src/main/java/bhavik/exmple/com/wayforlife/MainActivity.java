package bhavik.exmple.com.wayforlife;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public void joinbutton(View view){

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSds5LLkINVnDYxdwP-BjWmH5T5VNYNJbzmjDEIpBGtX7pbaiA/viewform"));
        Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
        startActivity(browserChooserIntent );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id==R.id.activity_about_us)
        {
            Intent follow=new Intent(this,about_us.class);
            startActivity(follow);
            return true;
        }
        else if(id==R.id.activity_admin_loging)
        {
            Intent adminlogin=new Intent(this,admin_login.class);
            startActivity(adminlogin);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.news_feed) {
            Intent follow=new Intent(this,News.class);
            startActivity(follow);
        } else if (id == R.id.blood_reqest) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.friends2support.org"));
            Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
            startActivity(browserChooserIntent );
        } else if (id == R.id.report_problem) {
            Intent follow=new Intent(this,Report.class);
            startActivity(follow);

        } else if (id == R.id.donate) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m-lp.co/wayoflif"));
            Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
            startActivity(browserChooserIntent );

        }else if(id==R.id.follow)
        {
            Intent follow=new Intent(this,ActivityFollowUs.class);
            startActivity(follow);
        }
        else if(id==R.id.events)
        {
            Intent follow=new Intent(this,Events.class);
            startActivity(follow);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
