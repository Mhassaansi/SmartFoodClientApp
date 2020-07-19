package wahcanttadmintailors.com.smartfoodorderingclientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments.welcome;

public class Login_base extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_base);
        sharedPreferences = getSharedPreferences(PreferenceClass.user,MODE_PRIVATE);
        String getUserType = sharedPreferences.getString(PreferenceClass.USER_TYPE,"");
        boolean getLoINSession = sharedPreferences.getBoolean(PreferenceClass.IS_LOGIN,false);
        Intent it=getIntent();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(!getLoINSession){
            welcome sigin=new welcome();
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.login_host,sigin);
            ft.commit();
        }
        else {
//            MainFragment sigin=new MainFragment();
//            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.ui,sigin);
//            ft.commit();
            Intent i=new Intent(Login_base.this,FragmentHostActivity.class);
            startActivity(i);
        }
    }
}
