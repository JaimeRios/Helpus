package com.android.jshuin.todorespuestas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgregarRespuesta extends AppCompatActivity {

    int questionSelected;
    @BindView(R.id.ListaRestitulo)
    TextView titulo;
    @BindView(R.id.ListaResrespuesta)
    EditText nuevaRespuesta;
    @BindView(R.id.ListaRescorreo)
    EditText correo;
    @BindView(R.id.ListaReslistaRespuestas)
    ListView listaRespuestas;
    @BindView(R.id.ListaResAgregarRespuesta)
    Button AgregarResButton;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    ArrayList<String> listaRespuestaGlobal = new ArrayList<String>();

    private static final String URL= "http://192.168.1.5:8888/Clientesres/responder.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_respuesta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        requestQueue= Volley.newRequestQueue(this);
        questionSelected = getIntent().getExtras().getInt("questionSelected");
        viewSettings();


    }

    private void addanswer() {
        DateFormat df = new SimpleDateFormat("MMMM dd, yyyy, HH:mm");
        final String date = df.format(Calendar.getInstance().getTime());
        stringRequest= new StringRequest(Request.Method.POST, URL, new
                Response.Listener<String>(){
                    @Override
                    public void onResponse (String Response) {
                        //Toast.makeText(getApplicationContext(), "su respuesta ha sido ingresada", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(getApplicationContext(),""+error,Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                HashMap<String,String> envio= new HashMap<>();
                envio.put("correo",correo.getText().toString());
                envio.put("respuesta",nuevaRespuesta.getText().toString());
                envio.put("fecha",date);
                envio.put("id",""+questionSelected);
                return  envio;

            }
        };
        requestQueue.add(stringRequest);
    }

    private void viewSettings() {

        questionSelected = getIntent().getIntExtra("questionSelected",0);
        titulo.setText("Tema de la pregunta");
        Toast.makeText(getApplicationContext(),"Pregunta seleccionada: "+questionSelected,Toast.LENGTH_SHORT).show();
        loadSharePreferences();
        loadAnswers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        switch (id){
            case R.id.Sobrenosotros:
                AlertDialog.Builder dialog = new AlertDialog.Builder(AgregarRespuesta.this);
                dialog.setMessage(R.string.sobreNosotros)
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Nothing
                            }
                        });
                AlertDialog mensaje = dialog.create();
                mensaje.setTitle("Sobre Nosotros");
                mensaje.show();
                return true;
            case R.id.SobreAplicacion:
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(AgregarRespuesta.this);
                dialog2.setMessage(R.string.sobreAplicacion)
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Nothing
                            }
                        });
                AlertDialog mensaje2 = dialog2.create();
                mensaje2.setTitle("Sobre La Aplicación");
                mensaje2.show();
                return true;
            case R.id.cerrarSesion:
                Toast.makeText(getApplicationContext(),"cerrando sesion",Toast.LENGTH_SHORT).show();
                Intent irAMain = new Intent(AgregarRespuesta.this,MainActivity.class);
                irAMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(irAMain);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //metodo que llama el boton registrar respuesta
    @OnClick(R.id.ListaResAgregarRespuesta)
    public void registrarRespuesta(){
        if(nuevaRespuesta.getText().toString().matches("")||correo.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Por favor complete toda la informacion",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Tu respuesta fue registrada",Toast.LENGTH_SHORT).show();
            addanswer();
            loadAnswers();
        }

    }

    public void loadAnswers(){
        Toast.makeText(getApplicationContext(), "Bienvenido" , Toast.LENGTH_LONG).show();
        final ArrayList<String> arrayList = new ArrayList<String>();
        listaRespuestaGlobal.clear();
        final String [] argumento = {Integer.toString(questionSelected)};
        JsonArrayRequest request = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject objeto = response.getJSONObject(i);
                        //String argumento = objeto.getString("id");
                        String respuesta = objeto.getString("respuesta");
                        String correo = objeto.getString("correo");
                        String fecha = objeto.getString("fecha");
                        listaRespuestaGlobal.add( respuesta + "\n" + "Por " + correo + " a las " + fecha);
                        arrayList.add(respuesta + "\n" + "Por " + correo + " a las " + fecha + i);
                        Toast.makeText(getApplicationContext(), respuesta+"\n"+"Por "+correo+" a las "+fecha, Toast.LENGTH_LONG).show();

                    }
                        catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error Json: " + e, Toast.LENGTH_LONG).show();

                    }
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.text_in_cells, arrayList);
                listaRespuestas.setAdapter(arrayAdapter);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error volley: " + error, Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(request);

    }


    private void loadSharePreferences() {
        SharedPreferences datos = getSharedPreferences("datosusuario", Context.MODE_PRIVATE);
        correo.setText(datos.getString("correo",""));
        SharedPreferences.Editor editor = datos.edit();
        editor.putString("correo",correo.getText().toString());
        editor.commit();
    }

}
