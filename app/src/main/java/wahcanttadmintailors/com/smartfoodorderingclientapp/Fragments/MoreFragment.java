package wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;

import wahcanttadmintailors.com.smartfoodorderingclientapp.Activites.FragmentHostActivity;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Activites.Login_base;
import wahcanttadmintailors.com.smartfoodorderingclientapp.javaclasses.PreferenceClass;
import wahcanttadmintailors.com.smartfoodorderingclientapp.R;


public class MoreFragment extends Fragment {
    ListView listView;
    String[] listItem;
    TextView textView;
    SharedPreferences sharedPreferences;
    MaterialToolbar materialToolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
     View v=inflater.inflate(R.layout.morefragemnt,container,false);
     sharedPreferences=getActivity().getSharedPreferences(PreferenceClass.user,
             Context.MODE_PRIVATE);

        materialToolbar=(MaterialToolbar)v.findViewById(R.id.catAppBar);
        ((FragmentHostActivity) getActivity()).setSupportActionBar(materialToolbar);
        materialToolbar.setTitle("More Items");
        listView=(ListView)v.findViewById(R.id.listmore);
        textView=(TextView)v.findViewById(R.id.moretext);
        listItem = getResources().getStringArray(R.array.Morelist);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value=adapter.getItem(position);
               // Toast.makeText(getContext(),value,Toast.LENGTH_SHORT).show();
                if(position==0)
                {
                    Fragment book_frag= new BookTableFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.ui, book_frag);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

                if(position==1){
                    Fragment book_frag= new DealsFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.ui, book_frag);
                    transaction.addToBackStack(null);
                    transaction.commit();


                }
                if(position==2){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(PreferenceClass.pre_email, "");
                    editor.putString(PreferenceClass.pre_pass, "");
                    editor.putString(PreferenceClass.user_token,"");
                    editor.putBoolean(PreferenceClass.IS_LOGIN, false);
                    editor.commit();

                    if (getActivity() != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().
                                remove(getParentFragment()).commit();
                        try {
                            getFragmentManager().popBackStack();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    Intent it=new Intent(getActivity(), Login_base.class);
                    startActivity(it);

                }
            }
        });







     return v;
    }
}
