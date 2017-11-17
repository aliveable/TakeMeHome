package kmitl.proj.jittakan58070012.takemehomedemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity {

    public static Activity activity;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Intent intent;
    private CommonSharePreference commonSharePreference;
    public static AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;

    @Override
    protected void onResume() {
        super.onResume();
        if (commonSharePreference.read("State").equals("Complete")){
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initcontrol();
        facebookLogin();

        Log.d("State", "onCreate: "+ commonSharePreference.read("State"));




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void facebookLogin(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                commonSharePreference.save("State", "Complete");
                commonSharePreference.save("CreateFragState", "First");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }

        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                updateWithToken(newAccessToken);
            }
        };


    }




    public void initcontrol(){
        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        intent = new Intent(this, User_Drawer_option.class);
        commonSharePreference = new CommonSharePreference(this);
        updateWithToken(AccessToken.getCurrentAccessToken());

    }

    private void updateWithToken(AccessToken currentAccessToken) {

        if (currentAccessToken != null) {
            commonSharePreference.save("State", "Complete");
        } else {
            commonSharePreference.save("State", "Logout");
        }
    }


}
