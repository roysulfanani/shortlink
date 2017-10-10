package com.example.android.shortlink;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class login extends AppCompatActivity {

    Button buttonLogin, buttonDaftar;
    EditText txtusername,txtpassword;
    String konf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusername     =(EditText) findViewById(R.id.txtnama);
        txtpassword     =(EditText) findViewById(R.id.txtpass);
        buttonLogin     =(Button) findViewById(R.id.btnlogin);
        buttonDaftar    =(Button) findViewById(R.id.btndaftar);

        final RequestQueue queue= Volley.newRequestQueue(login.this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = txtusername.getText().toString();
                final String password = txtpassword.getText().toString();

                String url = "http://linkshort.16mb.com/login.php";

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("lala", response);
                        //Toast.makeText(login.this, response, Toast.LENGTH_LONG).show();
                        try {
                            JSONObject respon = new JSONObject(response);

                            Toast.makeText(login.this, respon.getString("message"), Toast.LENGTH_LONG).show();

                            //konf = (EditText) findViewById(R.id.shortlink);

                            String shortUrl = respon.getString("shortlink");
                            //konf.setText(shortUrl);

                            /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setCancelable(false)
                                    .setMessage("Berhasil : " + shortUrl)
                                    .setPositiveButton("Oke sip", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();*/

                            if (shortUrl.equals("sukses")) {
                                Intent intent = new Intent(login.this, MainActivity.class);
                                startActivity(intent);
                            } else  {
                                Toast.makeText(login.this, respon.getString("message"), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(login.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> data = new HashMap<String, String>();
                        data.put("username", username);
                        data.put("password", password);

                        return data;
                    }
                };
                queue.add(request);
            }

        });

        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, daftar_akun.class);
                startActivity(intent);
            }
        });

    }
}
