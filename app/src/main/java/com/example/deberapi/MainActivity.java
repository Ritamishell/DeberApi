package com.example.deberapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.deberapi.Servidorweb.Asynchtask;
import com.example.deberapi.Servidorweb.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btmostrar (View view){
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> informacion = new HashMap<String, String>();
        WebService ws= new WebService(" https://jsonplaceholder.typicode.com/users",informacion, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }
    @Override
    public void processFinish(String result) throws JSONException {
        TextView Correo = findViewById(R.id.txtcorreos);
        JSONArray JSONlista = new JSONArray(result);
        StringBuilder lstinfoBuilder = new StringBuilder();
        for (int i = 0; i < JSONlista.length(); i++) {
            JSONObject usuario = JSONlista.getJSONObject(i);
            String correo = usuario.getString("email");
            lstinfoBuilder.append(" (").append(i).append(") ").append(correo).append(" \n");
            final int delay = (i + 1) * 800;
            new android.os.Handler().postDelayed(new Runnable() {
                public void run() {
                    Correo.setText(lstinfoBuilder.toString());
                    if (delay < JSONlista.length() - 1) {
                        Correo.append("\n");
                    }
                }
            }, delay);
        }
        String lstinfo = lstinfoBuilder.toString();
        }

    }
