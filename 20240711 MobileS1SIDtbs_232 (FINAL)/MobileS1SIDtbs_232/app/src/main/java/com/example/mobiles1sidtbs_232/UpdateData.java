package com.example.mobiles1sidtbs_232;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateData extends AppCompatActivity {

    //deklarasi variabel
    private EditText code_txtNIM, code_txtNama;
    private Button code_btnUbah;
    private TextView code_txtStatus;

    //deklarasi variabel tambahan
    private String URL = "http://172.16.60.99/mobile_dtbs/update.php";
    //Catatan: URL di atas tolong disesuaikan dengan IP4 kalian, cek di Command Prompt, ketik ipconfig

    //Stringrequest salah satu library volley utk menangkap data
    StringRequest stringRequest;
    RequestQueue requestQueue;

    //deklarasi variabel untuk temporary data
    private String nim, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //konfigurasi
        code_txtNIM = (EditText)findViewById(R.id.txtNIM);
        code_txtNama = (EditText)findViewById(R.id.txtNama);
        code_btnUbah = (Button)findViewById(R.id.btnUbah);
        code_txtStatus = (TextView) findViewById(R.id.txtStatus);

        //ketikan btnUbah diklik
        code_btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //selalu mulai dengan try catch
                try {
                    //untuk memastikan data yang terkirim sudah terisi di EditText, maka EditText tidak boleh kosong
                    //tampung data dari EditText ke variabel
                    nim = code_txtNIM.getText().toString().trim();
                    nama = code_txtNama.getText().toString().trim();

                    //cek terlebih dahulu jika inputan kosong atau tidak
                    if (nim.equals("") || nama.equals("")) //jika inputan kosong
                    {
                        code_txtStatus.setText("Inputan tidak lengkap"); //cara 1 dengan TextView
                        //Toast.makeText(getApplicationContext(), "Tidak ada inputan", Toast.LENGTH_LONG); //cara 2 dengan Toast
                    }
                    else
                    {
                        //instance of class
                        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //cek apakah sudah terintegrasi dengan PHP dan menampilkan statusnya
                                code_txtStatus.setText(response);
                                //Toast.makeText(getApplicationContext(), txtStatus_code.getText().toString(), Toast.LENGTH_LONG).show();

                                //cek apakah response = "Update" atau "Gagal Update"
                                //jika response = "Update", akan muncul Toast Data Berhasil Update
                                if (response.equals("Update"))
                                    //membuat Toast untuk pesan data diubah
                                    Toast.makeText(getApplicationContext(), "Data Berhasil Diubah", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getApplicationContext(), "Data Gagal Diubah", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                code_txtStatus.setText("masuk di method onErrorResponse()");

                            }
                        })
                        {
                            //anda cukup menuliskan "map", lalu enter. Otomatis method getParams akan terbentuk
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                //jika script masih ada warning, brarti blom di transfer menggunakan method POST
                                //oleh karena itu, dibutuhkan 3 baris script ini untuk mengirim data ke login.php
                                Map<String, String> data = new HashMap<>();
                                data.put("nim", nim);
                                data.put("nama", nama);
                                return data;
                            }
                        };

                        //request ke Volley
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                };
            }
        });


    }
}