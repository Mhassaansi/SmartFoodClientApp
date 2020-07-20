package wahcanttadmintailors.com.smartfoodorderingclientapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments.CategoryFragment;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments.MainFragment;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments.MoreFragment;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentHostActivity extends AppCompatActivity {

   public static BottomNavigationView bnv ;
    public static BadgeDrawable badgeDrawable;
   FrameLayout frameLayout;
//    View noti_badge;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                //private Object CategoryFragment;

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new MainFragment();

                            break;
                        case R.id.nav_menu:
                            selectedFragment = new CategoryFragment();


                            break;

                        case R.id.nav_more:
                            selectedFragment = new MoreFragment();
                            break;

                    }
                getSupportFragmentManager().beginTransaction().replace(R.id.ui,
                            selectedFragment).commit();
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);

        ///////////////
            MainFragment sigin=new MainFragment();
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.ui,sigin);
            ft.commit();
        //////////////////////////////

        bnv=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(navListener);




    }


}
