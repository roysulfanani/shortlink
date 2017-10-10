package com.example.android.shortlink;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class database_list_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_list_view);

        final RequestQueue queue = Volley.newRequestQueue(database_list_view.this);

        String url = "http://10.0.2.2/shortlink/tampildata.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("lala", response);

                try {
                    JSONObject respon = new JSONObject(response);

                    Toast.makeText(database_list_view.this, respon.getString("message"), Toast.LENGTH_LONG).show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(database_list_view.this);
                    builder.setCancelable(false)
                            //.setMessage("Berhasil : " + shortUrl)
                            .setPositiveButton("Oke sip", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError){
                Toast.makeText(database_list_view.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<String, String>();
                //data.put("linkasli", linkshort);
                return data;
            }
        };
        queue.add(request);

    }
}
