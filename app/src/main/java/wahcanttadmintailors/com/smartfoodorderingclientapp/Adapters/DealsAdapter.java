package wahcanttadmintailors.com.smartfoodorderingclientapp.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import wahcanttadmintailors.com.smartfoodorderingclientapp.FragmentHostActivity;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments.DealsDescriptionFragment;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Model.DealsModel;
import wahcanttadmintailors.com.smartfoodorderingclientapp.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static wahcanttadmintailors.com.smartfoodorderingclientapp.ApiUrls.deal_img;


public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealsViewHolder>{
    Context c;
    ArrayList<DealsModel> model;
    public DealsAdapter(Context c, ArrayList<DealsModel>model){
        this.c=c;
        this.model=model;
    }



    @NonNull
    @Override
    public DealsAdapter.DealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.test_deals,parent,false);
        DealsViewHolder dealsViewHolder=new DealsViewHolder(v,c,model);
        return dealsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DealsViewHolder holder, int position) {
        DealsModel dm=model.get(position);

        Picasso.get().load(deal_img+dm.getDeal_img()).into(holder.img);
        holder.deal_name.setText(dm.getDeal_name());

    }

    @Override
    public int getItemCount() {
        return model.size();
    }
    public class DealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView img;
        public TextView deal_name;
        public TextView deal_des;
        Context cc;
        ArrayList<DealsModel>cm;
        public DealsViewHolder(@NonNull View itemView,Context cc,ArrayList<DealsModel>cm) {
            super(itemView);
            this.cc=cc;
            this.cm=cm;
            img=(ImageView)itemView.findViewById(R.id.testimg);
            deal_name=(TextView)itemView.findViewById(R.id.dealname);
            deal_des=(TextView)itemView.findViewById(R.id.deal_description);
        }

        @Override
        public void onClick(View v) {
            int positon=getAdapterPosition();
            DealsModel pm1=this.cm.get(positon);
                    //deals data send to details fragment
                    DealsDescriptionFragment ddf=new DealsDescriptionFragment();
                    FragmentHostActivity activity_frag=(FragmentHostActivity) v.getContext();
                    Bundle b=new Bundle();
                    b.putString("e",pm1.getId());
                    b.putString("a",pm1.getDeal_name());
                    b.putString("b",pm1.getDeal_price());
                    b.putString("c",String.valueOf(pm1.getDeal_img()));
                    b.putString("f",pm1.getDeal_disc());
                    ddf.setArguments(b);
                    activity_frag.getSupportFragmentManager().beginTransaction().replace(R.id.ui,
                            ddf).addToBackStack(null).commit();

            }
//            activity_frag.getSupportFragmentManager().beginTransaction().replace(R.id.ui,
//                    pdf).addToBackStack(null).commit();
        }

    }

