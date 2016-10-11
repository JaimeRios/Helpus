package com.android.jshuin.todorespuestas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;



public class ListaPreguntas extends AppCompatActivity {

    @BindView(R.id.ListaPrelistaTemas)
    ListView listaPreguntas;
    ArrayList<String> listaPreguntaGlobal = new ArrayList<String>();

    RequestQueue requestQueue;

    private static final String URL = "http://192.168.1.5:8888/Clientesres/consultarpregunta.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_preguntas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ButterKnife.bind(this);

        requestQueue = Volley.newRequestQueue(this);
        loadquestions();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irAgregarTema = new Intent(ListaPreguntas.this, AgregarTema.class);
                startActivity(irAgregarTema);
            }
        });
    }

    private void loadquestions() {

        final ArrayList<String> arrayList = new ArrayList<String>();
        listaPreguntaGlobal.clear();
        JsonArrayRequest request = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject objeto = response.getJSONObject(i);
                        String id = objeto.getString("id");
                        String tema = objeto.getString("tema");
                        String pregunta = objeto.getString("pregunta");
                        String correo = objeto.getString("correo");
                        String hora = objeto.getString("hora");
                        listaPreguntaGlobal.add(tema + "\n" + pregunta + "\n" + "Por " + correo + " a las " + hora);
                        arrayList.add(tema + "\n" + pregunta + "\n" + "Por " + correo + " a las " + hora + i);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error Json: " + e, Toast.LENGTH_LONG).show();

                    }
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.text_in_cells, arrayList);
                listaPreguntas.setAdapter(arrayAdapter);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error volley: " + error, Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(request);

        listaPreguntas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent irAgregarRespuesta = new Intent(ListaPreguntas.this, AgregarRespuesta.class);
                irAgregarRespuesta.putExtra("questionSelected", i);
                startActivity(irAgregarRespuesta);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.Sobrenosotros:
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListaPreguntas.this);
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
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(ListaPreguntas.this);
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
                Toast.makeText(getApplicationContext(), "cerrando sesion", Toast.LENGTH_SHORT).show();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }





}
