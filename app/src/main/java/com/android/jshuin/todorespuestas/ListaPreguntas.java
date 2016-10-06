package com.android.jshuin.todorespuestas;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ListaPreguntas extends AppCompatActivity {

    @BindView(R.id.ListaPrelistaTemas)
    ListView listaPreguntas;
    ArrayList<String> preguntas = new ArrayList<>();

    RequestQueue requestQueue;
    StringRequest stringRequest;

    private static final String URL= "http://192.168.1.5:8888/Clientesres/consultarpreguta.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_preguntas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        ButterKnife.bind(this);
        requestQueue= Volley.newRequestQueue(this);

        loadQuestions();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irAgregarTema = new Intent(ListaPreguntas.this,AgregarTema.class);
                startActivity(irAgregarTema);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do Nothing
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        switch (id){
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
                Toast.makeText(getApplicationContext(),"cerrando sesion",Toast.LENGTH_SHORT).show();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void loadQuestions(){

        stringRequest= new StringRequest(Request.Method.POST, URL, new
                Response.Listener<String>(){
                    @Override
                    public void onResponse (String Response) {
                        try {
                            JSONObject jsonObject = new JSONObject(Response);

                            if (jsonObject.names().get(0).equals("logueado")) {
                                String correousu = jsonObject.getString("logueado");
                                SharedPreferences guardar =getSharedPreferences("datosusuario", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = guardar.edit();
                                editor.putString("correo", correousu);
                                editor.commit();
                                //Toast.makeText(getApplicationContext(), "Bienvenido" + correousu, Toast.LENGTH_LONG).show();
                                //startActivity(new Intent(getApplicationContext(),ListaPreguntas.class));
                            }
                            else{

                                //Toast.makeText(getApplicationContext(),"Error"+jsonObject.getString("error"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            //
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(getApplicationContext(),""+error,Toast.LENGTH_LONG).show();

            }
        }){
            /*@Override
            protected Map<String,String> getParams()throws AuthFailureError {
                HashMap<String,String> envio= new HashMap<>();
                envio.put("usuario",nombreUsuario.getText().toString());
                envio.put("contra",contrasena.getText().toString());
                return  envio;

            }*/
        };
        requestQueue.add(stringRequest);

        List<String> arrayList = new ArrayList<String>();
        for (int index=0;index<20;index++){
            arrayList.add("Pregunta #"+index);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arrayList );
        listaPreguntas.setAdapter(arrayAdapter);

        listaPreguntas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent irAgregarRespuesta = new Intent(ListaPreguntas.this,AgregarRespuesta.class);
                irAgregarRespuesta.putExtra("questionSelected",i);
                startActivity(irAgregarRespuesta);
            }
        });

    }

}
