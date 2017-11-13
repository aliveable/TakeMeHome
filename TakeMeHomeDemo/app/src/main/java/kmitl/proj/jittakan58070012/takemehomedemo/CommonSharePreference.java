package kmitl.proj.jittakan58070012.takemehomedemo;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class CommonSharePreference {
    public static final String NAME = "UserInfoApp";
    private SharedPreferences sharedPreferences;

    public CommonSharePreference(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void save(String key, String state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, state);
        editor.apply();
    }

    public Object read(String key ) {
        String result = sharedPreferences.getString(key,null);
        return result;
    }
}

