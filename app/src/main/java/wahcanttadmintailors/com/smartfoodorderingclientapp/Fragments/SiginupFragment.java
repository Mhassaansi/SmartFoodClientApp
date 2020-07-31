package wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import wahcanttadmintailors.com.smartfoodorderingclientapp.Activites.Login_base;
import wahcanttadmintailors.com.smartfoodorderingclientapp.R;

import static wahcanttadmintailors.com.smartfoodorderingclientapp.javaclasses.ApiUrls.sign_up;


public class SiginupFragment extends Fragment {
    EditText name,email,password,confirm;
    String nm_register,email_register,pass_register,confirm_pass;
    Button sigup_btn,already_singup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.signup,container,false);
        name=(EditText)v.findViewById(R.id.name_signup);
        email=(EditText)v.findViewById(R.id.email_signup);
        password=(EditText)v.findViewById(R.id.password_signup);
        already_singup=(Button) v.findViewById(R.id.already_signup);
        sigup_btn=(Button)v.findViewById(R.id.signup_btn);
        confirm=(EditText)v.findViewById(R.id.password_signup_confirm);

        nm_register=name.getText().toString();
        email_register=email.getText().toString();
        pass_register= password.getText().toString();
        confirm_pass= confirm.getText().toString();


        already_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiginFragment sigin=new SiginFragment();
                Login_base activity_frag=(Login_base) getActivity();
                activity_frag.getSupportFragmentManager().beginTransaction().replace(R.id.login_host,
                        sigin).addToBackStack(null).commit();
            }
        });

        sigup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")||email.getText().toString().equals("")
                        ||password.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Please Fill all Feilds",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Singup();
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    confirm.setText("");
                }
            }
        });

      //  bnv.setVisibility(View.GONE);

        return v;
    }

    private void Singup() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,sign_up,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("Responce:", response);
                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.optString("code");
                            String message = jsonObject.optString("message");
                            Toast.makeText(getActivity(), "Data save Sucssefully",
                                    Toast.LENGTH_SHORT).show();
                            if(response.equals(code)){
                                        SiginFragment sigin=new SiginFragment();
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.ui,sigin);
        ft.commit();
                            }
                            //Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                           // finish();
                        } catch (JSONException e) {
                            Toast.makeText(getActivity(), " Failed", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){

                        if (error instanceof TimeoutError) {

                            Toast.makeText(getActivity(), "time", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            if(error.networkResponse.statusCode==422){
                                Toast.makeText(getActivity(), "The Email address already taken",
                                        Toast.LENGTH_SHORT).show();
                            }
                         else   if(error.networkResponse.statusCode==404){
                                Toast.makeText(getActivity(), "Server Down", Toast.LENGTH_SHORT).show();
                            }
                         //   Toast.makeText(getActivity(), "server", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {

                            Toast.makeText(getActivity(), "Network", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getActivity(), "parse", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "default server error ",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name.getText().toString());
                params.put("email",email.getText().toString());
                params.put("password",password.getText().toString());
                params.put("password_confirmation",confirm.getText().toString());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
