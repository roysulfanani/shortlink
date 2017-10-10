package com.example.android.shortlink;

import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText txtshort, shortlink;
    Button btnDialog, btnshort, btnGoogleLogin;
    String linkshort;
    Dialog dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtshort = (EditText) findViewById(R.id.txtinput);
        btnshort = (Button) findViewById(R.id.btnshort);
        final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        btnshort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkshort = txtshort.getText().toString();

                String url = "http://linkshort.16mb.com/insertdata.php";

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("lala", response);

                        try {
                            JSONObject respon = new JSONObject(response);

                            //Toast.makeText(MainActivity.this, respon.getString("message"), Toast.LENGTH_LONG).show();

                            //shortlink = (EditText) findViewById(R.id.shortlink);

                            String shortUrl = respon.getString("shortlink");
                            //shortlink.setText(shortUrl);

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setCancelable(false)
                                    .setTitle("Berhasil")
                                    .setMessage(shortUrl)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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
                        Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> data = new HashMap<String, String>();
                        data.put("linkasli", linkshort);
                        return data;
                    }
                };
                queue.add(request);

                //Intent intent = new Intent(MainActivity.this, DialogPopup.class);
                //intent.putExtra("shortlink", shortlink);

                //intent.putExtra("alamat",alamat);
                //startActivity(intent);


            }
        });

        // Configure Google Sign In
        //GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(getString(R.string.default_web_client_id))
                //.requestEmail()
                //.build();


    }

    //method untuk membuat halaman menjadi dialog
    /*public void CustomDialog(String shortLink){
        dia = new Dialog(MainActivity.this);
        dia.setTitle("Judul Dialog "+shortLink);
        dia.setCancelable(true);
        dia.show();*/

        //memanggil button but yang ada pada dialog
        /*Button but = (Button) dia.findViewById(R.id.button2);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss(); //keluar dialog
            }
        });*/
    //}

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate dari menu; disini akan menambahkan item menu pada Actionbar
        getMenuInflater().inflate(R.menu.menu, menu);//Memanggil file bernama menu di folder menu
        return true;
    }*/
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:

                Toast.makeText(getApplicationContext(),"Menu 2 dipilih",Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu2:
                Toast.makeText(getApplicationContext(),"Menu 2 dipilih",Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu3:
                Toast.makeText(getApplicationContext(),"Menu 3 dipilih",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
