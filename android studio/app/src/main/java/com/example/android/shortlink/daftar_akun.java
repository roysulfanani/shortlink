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

public class daftar_akun extends AppCompatActivity {
    EditText nama, email, pass, repass;
    Button daftar, close;
    String setnama, setemail, setpass, setrepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_akun);

        nama    = (EditText) findViewById(R.id.txtnama);
        email   = (EditText) findViewById(R.id.txtemail);
        pass    = (EditText) findViewById(R.id.txtpass);
        repass  = (EditText) findViewById(R.id.txtrepass);
        daftar  = (Button) findViewById(R.id.btndaftar);
        close   = (Button) findViewById(R.id.btnkembali);

        final RequestQueue queue = Volley.newRequestQueue(daftar_akun.this);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnama = nama.getText().toString();
                setemail = email.getText().toString();
                setpass = pass.getText().toString();
                setrepass = repass.getText().toString();

                String url = "http://linkshort.16mb.com/daftar_akun.php";

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("lala", response);
                        //Toast.makeText(daftar_akun.this, response, Toast.LENGTH_LONG).show();
                        try {
                            JSONObject respon = new JSONObject(response);

                            Toast.makeText(daftar_akun.this, respon.getString("message"), Toast.LENGTH_LONG).show();

                            //shortlink = (EditText) findViewById(R.id.shortlink);

                            String shortUrl = respon.getString("shortlink");
                            //shortlink.setText(shortUrl);

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
                                Intent intent = new Intent(daftar_akun.this, login.class);
                                startActivity(intent);
                            } else  {
                                Toast.makeText(daftar_akun.this, respon.getString("message"), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError){
                        Toast.makeText(daftar_akun.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> datae = new HashMap<String, String>();
                        datae.put("nama_user", setnama);
                        datae.put("email", setemail);
                        datae.put("password", setpass);
                        datae.put("repassword", setrepass);
                        return datae;
                    }
                };
                queue.add(request);
            }
        });
    }
}
