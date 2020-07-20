package wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import wahcanttadmintailors.com.smartfoodorderingclientapp.Adapters.CategoryAdapter;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Adapters.DealsAdapter;
import wahcanttadmintailors.com.smartfoodorderingclientapp.FragmentHostActivity;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Model.DealsModel;
import wahcanttadmintailors.com.smartfoodorderingclientapp.R;

import static wahcanttadmintailors.com.smartfoodorderingclientapp.ApiUrls.view_deals;

public class DealsFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Context c;
    RecyclerView.Adapter adapter;
    ArrayList<DealsModel> deal;
    MaterialToolbar materialToolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.dealsfragment,container,false);
        materialToolbar=(MaterialToolbar)v.findViewById(R.id.catAppBar);
        ((FragmentHostActivity) getActivity()).setSupportActionBar(materialToolbar);
        materialToolbar.setTitle("Deals");
        deal=new ArrayList<>();
        recyclerView=(RecyclerView)v.findViewById(R.id.dealsfrgment);
        deal=new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DealsAdapter(getActivity(), deal);
        recyclerView.setAdapter(adapter);
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
                                deal.add(new DealsModel(
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

                        } catch (JSONException ex) {
                            ex.printStackTrace();
                            progressDialog.dismiss();
                        }
                        adapter.notifyDataSetChanged();
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
}