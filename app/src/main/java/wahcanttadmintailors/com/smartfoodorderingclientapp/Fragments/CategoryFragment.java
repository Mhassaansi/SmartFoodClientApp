package wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
import wahcanttadmintailors.com.smartfoodorderingclientapp.Adapters.CategoryAdapter;

import wahcanttadmintailors.com.smartfoodorderingclientapp.FragmentHostActivity;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Model.CaetgoryModel;
import wahcanttadmintailors.com.smartfoodorderingclientapp.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static wahcanttadmintailors.com.smartfoodorderingclientapp.ApiUrls.category_Api;


public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    public Context c;
    ArrayList<CaetgoryModel> CategoriesList;
    MaterialToolbar materialToolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment_categories, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyel_card_main);
        CategoriesList=new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        materialToolbar=(MaterialToolbar)v.findViewById(R.id.catAppBar);
        ((FragmentHostActivity) getActivity()).setSupportActionBar(materialToolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CategoryAdapter(getActivity(), CategoriesList);
        recyclerView.setAdapter(adapter);
         loadcetagories();
      //((FragmentHostActivity) getActivity()).setActionBarTitle("Menu");
        //CategoryFragment.getActionBar().setTitle("Your Title");

      //  ((AppCompatActivity)c).getSupportActionBar().setTitle("Your Title");
        //Determine screen size
//        if ((getResources().getConfiguration().screenLayout & Configuration.UI_MODE_TYPE_MASK)
//                == Configuration.UI_MODE_TYPE_TELEVISION) {
//            Toast.makeText(getActivity(), "Large screen", Toast.LENGTH_LONG).show();
//            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
//        }
//        else if ((getResources().getConfiguration().screenLayout & Configuration.UI_MODE_TYPE_MASK)
//                == Configuration.UI_MODE_TYPE_NORMAL) {
//            Toast.makeText(getActivity(), "Normal sized screen", Toast.LENGTH_LONG).show();
//            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
//        }
//        else if ((getResources().getConfiguration().screenLayout & Configuration.UI_MODE_TYPE_MASK)
//                ==Configuration.SCREENLAYOUT_SIZE_SMALL) {
//            Toast.makeText(getActivity(), "Small sized screen", Toast.LENGTH_LONG).show();
//            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
//        }
//        else {
//            Toast.makeText(getActivity(), "Screen size is neither large, normal or small",
//                    Toast.LENGTH_LONG).show();
//            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        }
        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void loadcetagories() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        final RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, category_Api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
//converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {
                                //getting product object from json array
                                JSONObject category = array.getJSONObject(i);
                               // CaetgoryModel cm=new CaetgoryModel();
                                CategoriesList.add(new CaetgoryModel(

                                        category.getString("id"),
                                        category.getString("category_name"),
                                        category.getString("category_img"),
                                        category.getString("created_at"),
                                        category.getString("updated_at")


                                ));
                                requestQueue.stop();
                            }


                           // CategoriesList.add()
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "catch block", Toast.
                                    LENGTH_SHORT).show();
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
//                params.put("pdate1", lastweekdate);

                return params;
            }
        };
        //adding our stringrequest to queue
        Volley.newRequestQueue(getContext()).add(stringRequest);
        //return null;
    }



    }
