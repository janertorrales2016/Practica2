package uteq.student.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

import uteq.student.practica2.WebService.Asynchtask;
import uteq.student.practica2.WebService.WebService;


public class MainActivity extends AppCompatActivity implements Asynchtask {
    private BreakIterator txtSaludo;
     int valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btenviar(View view){
        Map<String,String> datos= new HashMap<String,String>();
        WebService ws = new WebService("https://restcountries.eu/rest/v2/all",
                datos,this, this);
        valor=1;
        ws.execute("GET");
    }
    public void btenviar2(View view){
        Map<String,String> datos= new HashMap<String,String>();
        WebService ws = new WebService("http://rallycoding.herokuapp.com/api/music_albums",
                datos,this, this);
        valor=2;
        ws.execute("GET");
    }
    public void btenviar3(View view){
        Map<String,String> datos= new HashMap<String,String>();
        WebService ws = new WebService("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random",
                datos,this, this);
        valor=3;
        ws.execute("GET","accept", "application/json", "x-rapidapi-key", "c73b9b1fbbmsh121fff1f20c9a71p1e64fejsnc3b78825a877",
                "x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com");
    }
    public void btenviar4(View view){
        Map<String,String> datos= new HashMap<String,String>();
        WebService ws = new WebService("https://fortnite-api.p.rapidapi.com/stats/zRotation",
                datos,this, this);
        valor=4;
        ws.execute("GET", "x-rapidapi-key", "c73b9b1fbbmsh121fff1f20c9a71p1e64fejsnc3b78825a877","x-rapidapi-host", "fortnite-api.p.rapidapi.com");
    }
    @Override
    public void processFinish(String result) throws JSONException {
            TextView txtre= (TextView)findViewById(R.id.txtresult);
            txtre.setText("");
            txtre.setMovementMethod(new ScrollingMovementMethod());
            if (valor==1){
                String lstciudad="";
                JSONArray JSONlista =  new JSONArray(result);
                for(int i=0; i< JSONlista.length();i++){
                    JSONObject ciudades=  JSONlista.getJSONObject(i);
                    lstciudad = lstciudad + "\n" + ciudades.getString("name").toString() +" : " + ciudades.getString("subregion").toString() +" : " + ciudades.getString("region").toString() ;
                }
                txtre.setText("Pais : SubRegion : Region" + "\n" +lstciudad);
            }else{
                if(valor==2){
                    String lstciudad="";
                    JSONArray JSONlista =  new JSONArray(result);
                    for(int i=0; i< JSONlista.length();i++){
                        JSONObject ciudades=  JSONlista.getJSONObject(i);
                        lstciudad = lstciudad + "\n" + ciudades.getString("title").toString() +" : " + ciudades.getString("artist").toString() +" : " + ciudades.getString("url").toString() + "\n";
                    }
                    txtre.setText("Album : Artista : Url" + "\n" +lstciudad);
                }else{
                    if(valor==3){
                        txtre.setText("Chuck Norris API" + "\n"+ result);

                    }else{
                        txtre.setText("Fortnite-API" + "\n"+ result);
                    }
                }
            }


    }
}