package wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.squareup.picasso.Picasso;

import wahcanttadmintailors.com.smartfoodorderingclientapp.R;

import static wahcanttadmintailors.com.smartfoodorderingclientapp.ApiUrls.deal_img;


public class DealsDescriptionFragment extends Fragment {
    String dealname,dealprice ,dealimage,dealquan,dealid,dealdescrip;
    int bundle_id;
    TextView dealnamedesp,dealdesp,dealquantity,totPrice,despprice,p_desp;
    ImageView dealmainimage,dealplus,dealminus;
    Button dealadd_tp_cart;
    int count=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {

//       int i=Integer.parseInt(image);
        View v= inflater.inflate(R.layout.productdetails,container,false);
        dealnamedesp=(TextView)v.findViewById(R.id.deal_prod_nm);
        dealdesp=(TextView)v.findViewById(R.id.deal_description);
        dealquantity=(TextView)v.findViewById(R.id.deal_quantity_amount);
        totPrice=(TextView)v.findViewById(R.id.total_desp_amnt);
      //  despprice=(TextView)v.findViewById(R.id.desp_price);
        dealmainimage=(ImageView)v.findViewById(R.id.deal_mainimage);
        dealadd_tp_cart=(Button)v.findViewById(R.id.deal_add_to_cart);
        dealplus=(ImageView)v.findViewById(R.id.deal_add) ;
        dealminus=(ImageView)v.findViewById(R.id.deal_minus);
        dealquan=dealquantity.getText().toString().trim();
        Bundle b = getArguments();
        dealname = b.getString("a");
        dealprice = b.getString("b");
        dealimage = b.getString("c");
        dealid=b.getString("e");
        dealdescrip=b.getString("f");
        Picasso.get().load(deal_img+dealimage).into(dealmainimage);
        dealplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                dealquantity.setText(String.valueOf(count));
                int mul=Integer.parseInt(dealprice)*count;
                totPrice.setText(String.valueOf(mul));

            }
        });
        dealminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealdesp.setText(dealdescrip);
                if(count==1)
                    dealminus.setEnabled(false);
                else {
                    count--;
                    dealquantity.setText(String.valueOf(count));
                    int mul=Integer.parseInt(dealprice)*count;
                    totPrice.setText(String.valueOf(mul));
                } }
        });
        dealnamedesp.setText(String.valueOf(dealname));
        despprice.setText(String.valueOf(dealprice));
        totPrice.setText(String.valueOf(dealprice));


        return v;
    }
}
