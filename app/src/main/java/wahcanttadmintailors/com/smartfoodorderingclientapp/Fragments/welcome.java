package wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import wahcanttadmintailors.com.smartfoodorderingclientapp.R;


public class welcome extends Fragment {
    Button wel_sigin,wel_singup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.welcome_screen,container,false);
        wel_sigin=(Button)v.findViewById(R.id.wel_login);
        wel_singup=(Button)v.findViewById(R.id.wel_singup);
        wel_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiginupFragment sigin=new SiginupFragment();
                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.login_host,sigin);
                ft.commit();
            }
        });
        wel_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiginFragment sigin=new SiginFragment();
                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.login_host,sigin);
                ft.commit();
            }
        });
        return v;
    }
}
