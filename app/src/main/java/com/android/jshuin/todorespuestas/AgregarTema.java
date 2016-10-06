package com.android.jshuin.todorespuestas;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgregarTema extends AppCompatActivity {

    @BindView(R.id.agregarTematituloTema)
    EditText titulo;
    @BindView(R.id.agregarTemapregunta)
    EditText pregunta;
    @BindView(R.id.agregarTemacorreo)
    EditText correo;
    @BindView(R.id.agregarTemaButtom)
    Button agregartema;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    private static final String URL= "http://192.168.1.5:8888/Clientesres/preguntar.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tema);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);{}
        ButterKnife.bind(this);

        requestQueue= Volley.newRequestQueue(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences datos = getSharedPreferences("datosusuario", Context.MODE_PRIVATE);
        correo.setText(datos.getString("correo",""));
        SharedPreferences.Editor editor = datos.edit();
        editor.putString("correo",correo.getText().toString());
        editor.commit();
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(AgregarTema.this);
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
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(AgregarTema.this);
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
                Intent irAMain = new Intent(AgregarTema.this,MainActivity.class);
                irAMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(irAMain);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //metodo llamado por presionar el boton agregar tema
    @OnClick(R.id.agregarTemaButtom)
    public void enviarTema(){
        if(titulo.getText().toString().matches("")||pregunta.getText().toString().matches("")||
                correo.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Por favor ingrese la informacion de todos los campos",Toast.LENGTH_SHORT).show();
        }else{
            //Envie la informacion a la base de datos aqui
            makeHttpRequest();
            Intent irAListaPreguntas = new Intent(AgregarTema.this,ListaPreguntas.class);
            irAListaPreguntas.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(irAListaPreguntas);
            finish();
        }

    }

    private void makeHttpRequest() {
        DateFormat df = new SimpleDateFormat("MMMM dd, yyyy, HH:mm");
        final String date = df.format(Calendar.getInstance().getTime());
        stringRequest= new StringRequest(Request.Method.POST, URL, new
                Response.Listener<String>(){
                    @Override
                    public void onResponse (String Response) {
                        //Toast.makeText(getApplicationContext(), "sus datos ha sido ingresado", Toast.LENGTH_LONG).show();
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
                envio.put("tema",titulo.getText().toString());
                envio.put("correo",correo.getText().toString());
                envio.put("pregunta",pregunta.getText().toString());
                envio.put("hora",date);
                return  envio;

            }
        };
        requestQueue.add(stringRequest);
    }

}
