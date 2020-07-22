package wahcanttadmintailors.com.smartfoodorderingclientapp.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import wahcanttadmintailors.com.smartfoodorderingclientapp.R;


public class SplashScreen extends AppCompatActivity {
    private static int sPLASH_TIME_OUT = 3700;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashScreen.this, Login_base.class);
                startActivity(it);
                finish();
            }
        }, sPLASH_TIME_OUT);


    }




}