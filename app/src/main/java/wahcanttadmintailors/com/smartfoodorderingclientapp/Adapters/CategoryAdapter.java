package wahcanttadmintailors.com.smartfoodorderingclientapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import wahcanttadmintailors.com.smartfoodorderingclientapp.Activites.FragmentHostActivity;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments.ProductDetailFragment;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments.ProductFragment;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Model.CaetgoryModel;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import wahcanttadmintailors.com.smartfoodorderingclientapp.R;


import static wahcanttadmintailors.com.smartfoodorderingclientapp.javaclasses.ApiUrls.catimg_path;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolderTest>{

    public static  String categoryid;
  final ArrayList<CaetgoryModel> model2;
  Context c;
    public CategoryAdapter(Context c, ArrayList<CaetgoryModel> model2){
        this.c=c;
        this.model2=model2;
    }

    @NonNull
    @Override
    public CategoryViewHolderTest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.category_cards,parent,false);
        CategoryViewHolderTest categoryViewHolderTest=new CategoryViewHolderTest(v,c,model2);
        return categoryViewHolderTest;
    }


    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.CategoryViewHolderTest holder,
                                 int position) {
       final CaetgoryModel t2m= model2.get(position);
        holder.cattxt.setText(t2m.getCategory_name());
        Picasso.get().load(catimg_path+t2m.getCategory_img()).into(holder.catimg);
    }
    @Override
    public int getItemCount() {
        return model2.size();
    }

    public class CategoryViewHolderTest extends RecyclerView.ViewHolder implements
            View.OnClickListener {
       // public EditText sreach;
        public CircleImageView catimg;
        public TextView cattxt;
     Context cc;
     ArrayList<CaetgoryModel>cm;
        public CategoryViewHolderTest(@NonNull View itemView,Context cc,ArrayList<CaetgoryModel>cm) {
            super(itemView);
            this.cm=cm;
            this.cc=cc;
            itemView.setOnClickListener(this);
            catimg=(CircleImageView) itemView.findViewById(R.id.category_img);
            cattxt=(TextView) itemView.findViewById(R.id.category_nm);
        }

        @Override
        public void onClick(View v) {
            int pos=getAdapterPosition();
            CaetgoryModel cm1=this.cm.get(pos);
        ProductFragment pf= new ProductFragment();
            ProductDetailFragment pdf=new ProductDetailFragment();
            FragmentHostActivity activity_frag=(FragmentHostActivity) v.getContext();
            categoryid=cm1.getId();
            activity_frag.getSupportFragmentManager().beginTransaction().replace(R.id.ui,
                    pf).addToBackStack(null).commit();

        }

    }
}
