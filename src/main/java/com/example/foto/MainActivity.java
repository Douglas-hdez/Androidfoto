package com.example.foto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.os.StrictMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    TextView sal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sal  = (TextView) findViewById(R.id.salida);
        getData();
    }


    public void getData(){
        String sql = "http://jsonplaceholder.typicode.com/users";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection conn;

        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            String json = "";

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            json = response.toString();

            JSONArray jsonArr = null;

            jsonArr = new JSONArray(json);
            String mensaje = "";
            for(int i = 0;i<jsonArr.length();i++){
                JSONObject jsonObject = jsonArr.getJSONObject(i);

                Log.d("SLIDA",jsonObject.optString("name"));
                mensaje += "Nombre:  "+" "+jsonObject.optString("name")+"\n";

                Log.d("SLIDA",jsonObject.optString("username"));
                mensaje += "Usuario:  "+" "+jsonObject.optString("username")+"\n";


                Log.d("SLIDA",jsonObject.optString("email"));
                mensaje += "Correo:    "+" "+jsonObject.optString("email")+"\n";

                Log.d("SLIDA",jsonObject.optString("phone"));
                mensaje += "Telefono: "+" "+jsonObject.optString("phone")+"\n"+"\n";


            }
            sal.setText(mensaje);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }






}
