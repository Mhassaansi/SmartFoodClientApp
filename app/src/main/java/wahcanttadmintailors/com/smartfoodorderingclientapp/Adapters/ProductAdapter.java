package wahcanttadmintailors.com.smartfoodorderingclientapp.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import wahcanttadmintailors.com.smartfoodorderingclientapp.FragmentHostActivity;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments.ProductDetailFragment;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Model.ProductModel;
import wahcanttadmintailors.com.smartfoodorderingclientapp.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static wahcanttadmintailors.com.smartfoodorderingclientapp.ApiUrls.prod_img;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductCardViewHolder>{
    Context c;
    ArrayList<ProductModel> model2;
    public ProductAdapter(Context c, ArrayList<ProductModel> model2){
        this.c=c;
        this.model2=model2;
    }


    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.product_card, parent, false);
        ProductCardViewHolder productCardViewHolder=new ProductCardViewHolder(v,c,model2);
        return productCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductCardViewHolder holder, int position) {
        final ProductModel t2m= model2.get(position);
        holder.tv_name.setText(t2m.getProduct_name());
        holder.tv_price.setText(t2m.getProduct_price());
        Picasso.get().load(prod_img+t2m.getProduct_img()).into(holder.im_image);
    }
    @Override
    public int getItemCount() {
        return model2.size();
    }
public class ProductCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {
    public TextView tv_name,tv_price;
    public ImageView im_image;
    public Button view_detials;
    Context c1;
    ArrayList<ProductModel> pm;


    public ProductCardViewHolder(@NonNull View itemView, Context c1, ArrayList<ProductModel> pm) {
        super(itemView);
        this.pm=pm;
        this.c1=c1;
        itemView.setOnClickListener(this);
        //  tv_size=(TextView)itemView.findViewById(R.id.foodsize);
        tv_price=(TextView)itemView.findViewById(R.id.foodprice);
        tv_name=(TextView)itemView.findViewById(R.id.foodname);
        im_image=(ImageView) itemView.findViewById(R.id.foodimg);
        view_detials=(Button)itemView.findViewById(R.id.viewdetails);
        view_detials.setOnClickListener(this);
        //view_detials.setVisibility(View.GONE);
    }
        @Override
        public void onClick(View v) {
            int positon=getAdapterPosition();
            ProductModel pm1=this.pm.get(positon);
        switch (v.getId()){

            case R.id.viewdetails:
                ProductDetailFragment pdf1=new ProductDetailFragment();
                FragmentHostActivity activity_frag1=(FragmentHostActivity) v.getContext();
                Bundle b1=new Bundle();
                b1.putString("e",pm1.getId());
                b1.putString("a",pm1.getProduct_name());
                b1.putString("b",pm1.getProduct_price());
//                b1.putString("g",pm1.getMedium_price());
//                b1.putString("h",pm1.getLarge_price());
                b1.putString("c",String.valueOf(pm1.getProduct_img()));
                b1.putString("d",pm1.getIngridient_id());
                b1.putString("f",pm1.getProduct_discription());
                pdf1.setArguments(b1);
                activity_frag1.getSupportFragmentManager().beginTransaction().replace(R.id.ui,
                        pdf1).addToBackStack(null).commit();
//boolean isExsits=new ProductsStorage(v.getContext()).checkFoodexsists(
//        Integer.parseInt(pm1.getId()));
//if(!isExsits)
//            {  new ProductsStorage(v.getContext()).addTocart(
//                        new CartModel(
//                                Integer.parseInt(pm1.getId()),
//                                pm1.product_name,
//                                "1",
//                                pm1.getLarge_price()
//                                ,prod_img+pm1.getProduct_img()
//                                ,"Large"
//
//                        ));}
//
//else {
//
//   new ProductsStorage(v.getContext()).increaseCart( Integer.parseInt(pm1.getId()));
//}
//                Toast.makeText(v.getContext(), "Item Succesfully add in cart:",
//                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.p_card:
                //product data send to details fragment
                ProductDetailFragment pdf=new ProductDetailFragment();
                FragmentHostActivity activity_frag=(FragmentHostActivity) v.getContext();
                Bundle b=new Bundle();
                b.putString("e",pm1.getId());
                b.putString("a",pm1.getProduct_name());
                b.putString("b",pm1.getProduct_price());
//                b.putString("g",pm1.getMedium_price());
//                b.putString("h",pm1.getLarge_price());
                b.putString("c",String.valueOf(pm1.getProduct_img()));
                b.putString("d",pm1.getIngridient_id());
                b.putString("f",pm1.getProduct_discription());
                pdf.setArguments(b);
                activity_frag.getSupportFragmentManager().beginTransaction().replace(R.id.ui,
                        pdf).addToBackStack(null).commit();
        }
        }
    }
}
