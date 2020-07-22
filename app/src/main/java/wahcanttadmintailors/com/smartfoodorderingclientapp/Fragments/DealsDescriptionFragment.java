package wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.squareup.picasso.Picasso;

import wahcanttadmintailors.com.smartfoodorderingclientapp.R;

import static wahcanttadmintailors.com.smartfoodorderingclientapp.javaclasses.ApiUrls.deal_img;


public class DealsDescriptionFragment extends Fragment {
    String dealname,dealprice,dealimage,dealdescrip;
    TextView dealnamedesp,dealdesp,dealdespprice;
    ImageView dealmainimage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {


        View v= inflater.inflate(R.layout.deals_the_month,container,false);
        dealnamedesp=(TextView)v.findViewById(R.id.deal_prod_nm);
        dealdesp=(TextView)v.findViewById(R.id.deal_description);
        dealmainimage=(ImageView)v.findViewById(R.id.deal_mainimage);
        dealdespprice=(TextView)v.findViewById(R.id.deal_price);

        Bundle b = getArguments();
        dealname = b.getString("a");
        dealprice = b.getString("b");
        dealimage = b.getString("c");
        dealdescrip=b.getString("f");
        Picasso.get().load(deal_img+dealimage).into(dealmainimage);
        dealnamedesp.setText(dealname);
        dealdesp.setText(dealdescrip);
        dealdespprice.setText(dealprice);
        return v;
    }
}
