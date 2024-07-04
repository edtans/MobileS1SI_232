package com.example.mobiles1sidtbs_232;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectData extends AppCompatActivity {

    //deklarasi variabel
    private TextView code_txtStatus;
    private ListView code_lvMahasiswa;

    //arrayadapter digunakan untuk menampung data dalam array
    private ArrayAdapter adapter_mahasiswa;

    //deklarasi variable untuk Json
    private JSONObject jsonObj; //digunakan untuk proses pengambilan data JSon
    private JSONArray jsonMahasiswa;
    private JSONObject jsonData;

    //deklarasi variabel tambahan
    private String URL = "http://172.16.44.149/mobile_dtbs/select.php";
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
        setContentView(R.layout.activity_select_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //konfigurasi
        code_txtStatus = (TextView) findViewById(R.id.txtStatus);
        code_lvMahasiswa = (ListView)findViewById(R.id.lvmahasiswa);

        //membuat constructor ArrayAdapter (dengan 2 parameter)
        //android.R.layout.simple_list_item_1 adalah layout bawaan dari Android sendiri
        adapter_mahasiswa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);

        //membuat data adapter
        adapter_mahasiswa.add("Adi");
        adapter_mahasiswa.add("Budi");
        adapter_mahasiswa.add("Cicil");

        //mengirim data adapter utk di tempatkan ke dalam List view menggunakan method setAdapter()
        code_lvMahasiswa.setAdapter(adapter_mahasiswa);

        //instance of class
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                code_txtStatus.setText("Sukses");

                //selalu mulai dengan try...catch...
                try {
                    //instance of class JSONObj
                    jsonObj = new JSONObject(response);
                    //instance of class JSONObj. Isi parameter berdasarkan dari nama array di JSON
                    jsonMahasiswa = jsonObj.getJSONArray("mahasiswa");

                    //hitung jumlah baris data
                    for (int i = 0; i < jsonMahasiswa.length(); i++) {
                        jsonData = jsonMahasiswa.getJSONObject(i);
                        //tampung data ke dalam variabel
                        nim = jsonData.getString("nim");
                        nama = jsonData.getString("nama");

                        //membuat data adapter menggunakan method add()
                        adapter_mahasiswa.add("NIM = " + nim + "\n" +
                                "Nama = " + nama);
                    }
                    //mengirim data adapter utk di tempatkan ke dalam List view menggunakan method setAdapter()
                    code_lvMahasiswa.setAdapter(adapter_mahasiswa);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                };

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //code_txtStatus.setText("Gagal");
                code_txtStatus.setText(error.toString());

            }
        });

        //request ke Volley
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);




    }
}