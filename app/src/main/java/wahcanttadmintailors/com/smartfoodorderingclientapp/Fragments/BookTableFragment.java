package wahcanttadmintailors.com.smartfoodorderingclientapp.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
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
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.shape.MaterialShapeDrawable;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import wahcanttadmintailors.com.smartfoodorderingclientapp.Activites.FragmentHostActivity;
import wahcanttadmintailors.com.smartfoodorderingclientapp.javaclasses.PreferenceClass;
import wahcanttadmintailors.com.smartfoodorderingclientapp.R;


import static wahcanttadmintailors.com.smartfoodorderingclientapp.javaclasses.ApiUrls.apply_book_tabel;


public class BookTableFragment extends Fragment {
    MaterialToolbar materialToolbar;
    TextView date,time;
    Button book;
    long diff,differenceDates;
    EditText totpersons;
  String Token;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         final View v=inflater.inflate(R.layout.book_table,container,false);
         date=v.findViewById(R.id.Date_Selection);
         time=v.findViewById(R.id.Time_Selection);
         book=v.findViewById(R.id.bookit);
         totpersons=v.findViewById(R.id.tot_persons);
//Text view desiginig
        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        shapeDrawable.setFillColor(ContextCompat.getColorStateList(getActivity(),
                android.R.color.transparent));
        shapeDrawable.setStroke(1.0f, ContextCompat.getColor(getActivity(),
                R.color.dark_Green));
        ViewCompat.setBackground(date,shapeDrawable);
        ViewCompat.setBackground(time,shapeDrawable);


        materialToolbar=(MaterialToolbar)v.findViewById(R.id.catAppBar);
        ((FragmentHostActivity) getActivity()).setSupportActionBar(materialToolbar);
        materialToolbar.setTitle("Book Your Table");

         sharedPreferences=getActivity().getSharedPreferences(PreferenceClass.user,
                 Context.MODE_PRIVATE);
        Token=sharedPreferences.getString(PreferenceClass.user_token,"token");
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year1, int month, int day) {

                                Calendar calendar1 = Calendar.getInstance();
                                calendar1.set(Calendar.YEAR, year1);
                                calendar1.set(Calendar.MONTH, month);
                                calendar1.set(Calendar.DAY_OF_MONTH, day);

                                Date dat = calendar1.getTime();
                                SimpleDateFormat dateFormatter = new SimpleDateFormat(
                                        "dd/MM/yyyy");
                                String  strDate = dateFormatter.format(dat);
                                Calendar calender=Calendar.getInstance();
                                SimpleDateFormat simpledate=new SimpleDateFormat("dd/MM/yyyy");
                                String  savecurrentdate=simpledate.format(calender.getTime());

                                SimpleDateFormat myFormat
                                        = new SimpleDateFormat("dd/MM/yyyy");
                                try {
                                    Date date1 = myFormat.parse(savecurrentdate);
                                    Date date2 = myFormat.parse(strDate);

                                    diff=  date2.getTime() - date1.getTime();
                                    differenceDates = diff / (24 * 60 * 60 * 1000);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                String dayDifference = Long.toString(differenceDates);
                                if (Integer.parseInt(dayDifference)<0){
                                    Toast.makeText(getActivity(), "Kindly Enter another Date",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    date.setText(strDate);
                                }


                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        final Calendar c = Calendar.getInstance();
      final int hour = c.get(Calendar.HOUR_OF_DAY);
         final int mnute = c.get(Calendar.MINUTE);
       time.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                       new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
time.setText(hourOfDay + ":" + minute);
                   }
               }, hour, mnute, false);

               timePickerDialog.show();
           }
       });

       book.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(date.getText().toString().equals("")||time.getText().toString().equals("")
                       ||totpersons.getText().toString().equals("")){
                   Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
               }
               else{

                   bookTable();
                   date.setText("");
                   time.setText("");
                   totpersons.setText("");
               }
           }
       });
         return  v;
    }
    private void bookTable () {
        final  String finaltime,finaldate,persons;
        finaldate=date.getText().toString().trim();
        finaltime=time.getText().toString().trim();
        persons=totpersons.getText().toString().trim();
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,apply_book_tabel,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("Responce:", response);
                            JSONObject jsonObject = new JSONObject(response);
                          String code = jsonObject.optString("code");
                           String message = jsonObject.optString("message");
                            // finish();
                        } catch (JSONException e) {
                            Toast.makeText(getActivity(),
                                    "We Will Mail You For Reservation Confirmation!",
                                    Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity(), "server", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {

                            Toast.makeText(getActivity(), "Network", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getActivity(), "parse", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "error ha bahi pr pta nahi",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("date",finaldate);
                params.put("time",finaltime);
                params.put("persons",persons);

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header =  new HashMap<>();
              //header.put("Content-Type","application/json");
                header.put("Authorization", "Bearer " + Token);
                return header;
           }
        };

        requestQueue.add(stringRequest);
    }


    }



