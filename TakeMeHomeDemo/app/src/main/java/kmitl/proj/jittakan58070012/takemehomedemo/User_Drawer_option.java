package kmitl.proj.jittakan58070012.takemehomedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import kmitl.proj.jittakan58070012.takemehomedemo.fragment.DriverFragment;
import kmitl.proj.jittakan58070012.takemehomedemo.fragment.HomeFragment;
import kmitl.proj.jittakan58070012.takemehomedemo.model.userProfile;

public class User_Drawer_option extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Intent intent;
    private CommonSharePreference commonSharePreference;

    public static userProfile userProfile;

    private View rootView;
    private AccessToken accessToken;
    private FragmentManager fragmentManager;

    public void initialFragmentHome(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.FragmentContainer,new HomeFragment(), "home").commit();
    }



    public void initialFragmentDriver(){
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("home");

        fragmentManager.beginTransaction().remove(fragment).replace(R.id.FragmentContainer,new DriverFragment()).addToBackStack(null).commit();

    }




    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    initialFragmentHome();
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    initialFragmentDriver();
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__drawer_option);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        
        commonSharePreference = new CommonSharePreference(this);


        userProfile = new userProfile();
        if (commonSharePreference.read("CreateFragState").equals("First")){
            initialFragmentHome();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        graph_request();
        

        //name.setText(userProfile.getName());

    }
    
    public void graph_request(){
        accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
                        final JSONObject jsonObject = response.getJSONObject();
                        setname(jsonObject.optString("name").toString());
                        setid(jsonObject.optString("id"));
                        setLink(jsonObject.optString("link"));
                        Log.v("output", object.toString());
                        Log.v("output2", jsonObject.opt("name").toString());
                        Log.v("output", userProfile.getName());
                    }
                });


        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void setname(String input_name){
        TextView name = findViewById(R.id.Name);
        userProfile.setName(input_name);
        name.setText(userProfile.getName().toString());
        Log.v("output in setnamemethod", userProfile.getName());
    }

    public void setid(String id){
        userProfile.setId(id);
    }

    public void setLink(String link){
        userProfile.setLink(link);

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
        getMenuInflater().inflate(R.menu.user__drawer_option, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.payment) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {

            intent = new Intent(this, MainActivity.class);
            LoginManager.getInstance().logOut();
            commonSharePreference.save("State", "Logout");
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}
