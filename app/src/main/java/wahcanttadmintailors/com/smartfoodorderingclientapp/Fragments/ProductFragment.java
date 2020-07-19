package wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Adapters.ProductAdapter;

import wahcanttadmintailors.com.smartfoodorderingclientapp.FragmentHostActivity;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Model.ProductModel;
import wahcanttadmintailors.com.smartfoodorderingclientapp.R;

import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static wahcanttadmintailors.com.smartfoodorderingclientapp.Adapters.CategoryAdapter.categoryid;
import static wahcanttadmintailors.com.smartfoodorderingclientapp.ApiUrls.products_Api;


public class ProductFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public Context c;
    ArrayList<ProductModel> productList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View v=inflater.inflate(R.layout.fragment_detials_fragment, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        productList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mAdapter=new ProductAdapter(c,productList);
        recyclerView.setAdapter(mAdapter);
        productdetails();
        MaterialToolbar materialToolbar;
        materialToolbar=(MaterialToolbar)v.findViewById(R.id.catAppBar);
        ((FragmentHostActivity) getActivity()).setSupportActionBar(materialToolbar);
        materialToolbar.setTitle("Food Items");
        return v;
    };



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void productdetails() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        final RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                products_Api+categoryid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
//converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {
                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                productList.add(new ProductModel(
                                        product.getString("id"),
                                        product.getString("product_name"),
                                       product.getString("product_price"),
                                        product.getString("product_discription"),
                                        product.getString("product_calories"),
                                        product.getString("category_id"),
                                        product.getString("product_img"),
                                        product.getString("created_at"),
                                        product.getString("updated_at")
                                ));
                                requestQueue.stop();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "catch block", Toast.
                                    LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        mAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof TimeoutError) {
                            Toast.makeText(getActivity(), "time", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getActivity(), "server", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            //toast or log your error
                            Toast.makeText(getActivity(), "Network", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getActivity(), "parse", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "error ha bahi pr pta nahi",
                                    Toast.LENGTH_SHORT).show();
                        }

                        requestQueue.stop();
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        //adding our stringrequest to queue
        Volley.newRequestQueue(getContext()).add(stringRequest);
        //return null;
    }


    }

