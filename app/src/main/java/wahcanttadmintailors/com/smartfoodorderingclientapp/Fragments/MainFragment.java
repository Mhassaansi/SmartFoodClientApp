package wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import wahcanttadmintailors.com.smartfoodorderingclientapp.Adapters.DealsAdapter;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Model.DealsModel;
import wahcanttadmintailors.com.smartfoodorderingclientapp.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static wahcanttadmintailors.com.smartfoodorderingclientapp.ApiUrls.view_deals;
public class MainFragment extends Fragment implements View.OnClickListener {
    Button booktabel,viewfood;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<DealsModel> dealsmodel;
    public Context c;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.main_activity,container,false);
        recyclerView=(RecyclerView)v.findViewById(R.id.maindeals);
        booktabel=(Button)v.findViewById(R.id.booktable);
        viewfood=(Button)v.findViewById(R.id.viewfood);



      booktabel.setOnClickListener(this);
        viewfood.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        dealsmodel=new ArrayList<>();
        mAdapter= new DealsAdapter(c,dealsmodel);
        recyclerView.setAdapter(mAdapter);
//        MaterialToolbar materialToolbar;
//        materialToolbar=(MaterialToolbar)v.findViewById(R.id.catAppBar);
//        ((FragmentHostActivity) getActivity()).setSupportActionBar(materialToolbar);
//        materialToolbar.setTitle("Home");
        dealsdata();

        return v;
    }

    private void dealsdata(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        final RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, view_deals,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {
                                //getting product object from json array
                                JSONObject deals = array.getJSONObject(i);
                                dealsmodel.add(new DealsModel(
                                        deals.getString("id"),
                                        deals.getString("deal_img"),
                                        deals.getString("deal_name"),
                                        deals.getString("deal_price"),
                                        deals.getString("deal_disc"),
                                        deals.getString("created_at"),
                                        deals.getString("updated_at")
                                ));
                                requestQueue.stop();
                            }
                            requestQueue.stop();
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                            progressDialog.dismiss();
                        }
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

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.booktable:
                Fragment book_frag= new BookTableFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.ui, book_frag);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case  R.id.viewfood:
                Fragment fragment1= new CategoryFragment();
                FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                transaction1.replace(R.id.ui, fragment1);
                transaction1.addToBackStack(null);
                transaction1.commit();
                break;
        }

    }
}
