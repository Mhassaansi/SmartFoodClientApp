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

import com.google.android.material.badge.BadgeDrawable;

import com.squareup.picasso.Picasso;

import wahcanttadmintailors.com.smartfoodorderingclientapp.R;

import static wahcanttadmintailors.com.smartfoodorderingclientapp.ApiUrls.prod_img;


public class ProductDetailFragment extends Fragment {
    String name,s,m,l,calories,image,descrip;
    TextView namedesp,desp,cal,totPrice,despprice;
    ImageView mainimage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.productdetails,container,false);
        namedesp=(TextView)v.findViewById(R.id.desp_nm);
        desp=(TextView)v.findViewById(R.id.desp_desp);
        cal=(TextView)v.findViewById(R.id.calories_amount);
        totPrice=(TextView)v.findViewById(R.id.total_desp_amnt);
        despprice=(TextView)v.findViewById(R.id.price_product);
        mainimage=(ImageView)v.findViewById(R.id.mainimage);




       Bundle b = getArguments();
        name = b.getString("a");
        s= b.getString("b");
        image = b.getString("c");
        calories =b.getString("d");
        descrip=b.getString("f");
        Picasso.get().load(prod_img+image).into(mainimage);
        desp.setText(descrip);
        despprice.setText(s);
        return v;
    }

    }

