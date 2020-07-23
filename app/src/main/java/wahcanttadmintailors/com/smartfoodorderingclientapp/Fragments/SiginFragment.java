package wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import wahcanttadmintailors.com.smartfoodorderingclientapp.Activites.FragmentHostActivity;
import wahcanttadmintailors.com.smartfoodorderingclientapp.Activites.Login_base;
import wahcanttadmintailors.com.smartfoodorderingclientapp.javaclasses.PreferenceClass;
import wahcanttadmintailors.com.smartfoodorderingclientapp.R;

import static wahcanttadmintailors.com.smartfoodorderingclientapp.javaclasses.ApiUrls.sign_in;


public class SiginFragment extends Fragment {
   public  EditText email,password;
    String email_register,pass_register;
    String TOKEN_CODE ;
    Button sigin_btn,notmember;
    SharedPreferences sPref;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signin, container, false);

        email = (EditText) v.findViewById(R.id.email);
        password = (EditText) v.findViewById(R.id.password);
        notmember = (Button) v.findViewById(R.id.not_member);
        sigin_btn=(Button)v.findViewById(R.id.sigin_btn);


        Login_base activity_frag= (Login_base) v.getContext();
        context=activity_frag;
        sPref =getActivity().getSharedPreferences(PreferenceClass.user, Context.MODE_PRIVATE);


        notmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiginupFragment siginup=new SiginupFragment();
                Login_base activity_frag=(Login_base) getActivity();
                activity_frag.getSupportFragmentManager().beginTransaction().replace(R.id.login_host,
                        siginup).addToBackStack(null).commit();
            }
        });
        sigin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") ||  password.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please Fill all Feilds", Toast.LENGTH_SHORT).show();
                } else {
                    Singin();
//                    email.setText("");
//                    password.setText("");
                }
            }
        });


            return v;
        }


        private void Singin () {
           final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            StringRequest stringRequest = new StringRequest(Request.Method.POST,sign_in,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("Responce:", response);
                              JSONObject jsonObject = new JSONObject(response);
                               TOKEN_CODE = jsonObject.optString("token");



if(response.equals("Invalid User")){
    Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
}
else{
    Toast.makeText(getActivity(), "LOGIN SUCCESFULLY",
            Toast.LENGTH_LONG).show();
    SharedPreferences.Editor editor = sPref.edit();
    editor.putString(PreferenceClass.pre_email, email.getText().toString());
    editor.putString(PreferenceClass.pre_pass, password.getText().toString());
    editor.putString(PreferenceClass.user_token,TOKEN_CODE);
    editor.putBoolean(PreferenceClass.IS_LOGIN, true);
    editor.commit();

    Intent i=new Intent(getContext(), FragmentHostActivity.class);
    getActivity().finish();
    startActivity(i);


//    if (getActivity() != null) {
//        getActivity().getSupportFragmentManager().beginTransaction().
//                remove(getParentFragment()).commit();
//        try {
//            getFragmentManager().popBackStack();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
                           //     requestQueue.stop();
                            } catch (JSONException e) {
                                Toast.makeText(getActivity(), " Response error", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            if (error instanceof TimeoutError) {
                                Toast.makeText(getActivity(), "time", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof ServerError) {
                                if(error.networkResponse.statusCode==404){
                                    Toast.makeText(getActivity(), "Server Down", Toast.LENGTH_SHORT).show();
                                }
                            }

                            else if (error instanceof NetworkError) {

                                Toast.makeText(getActivity(), "Network", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof ParseError) {
                                Toast.makeText(getActivity(), "parse", Toast.LENGTH_SHORT).show();
                            } else {
                                if(error.networkResponse.statusCode==401){
                                    Toast.makeText(getActivity(), "Invalid User", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("email", email.getText().toString());
                    params.put("password",  password.getText().toString());
                    return params;
                }
//                @Override
//                protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                    String TOKEN_CODE = response.headers.get("token");
//                    Toast.makeText(getActivity(), "toooooooooooooo"+TOKEN_CODE, Toast.LENGTH_SHORT).show();
//                    return null ;
//
//                }
            };
            requestQueue.add(stringRequest);
        }
//    private boolean checkEmail(String email) {
//        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
//    }
        }

