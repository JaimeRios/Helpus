package com.android.jshuin.todorespuestas;

import android.app.ProgressDialog;
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
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrarUsuario extends AppCompatActivity {

    @BindView(R.id.Registraringresarusuario)
    EditText nombreUsuario;
    @BindView(R.id.Registraringresarcontra)
    EditText contrasena1;
    @BindView(R.id.Registraringresarcontra2)
    EditText contrasena2;
    @BindView(R.id.Registraringresarcorreo)
    EditText correo;
    @BindView(R.id.RegistrarButtom)
    Button registrarUsuario;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    private static final String URL= "http://192.168.1.5:8888/Clientesres/insertar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        requestQueue= Volley.newRequestQueue(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        switch (id){
            case R.id.Sobrenosotros2:
                AlertDialog.Builder dialog = new AlertDialog.Builder(RegistrarUsuario.this);
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
            case R.id.SobreAplicacion2:
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(RegistrarUsuario.this);
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
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.RegistrarButtom)
    public void registrar(){
        if(nombreUsuario.getText().toString().matches("")||contrasena1.getText().toString().matches("")||
                contrasena2.getText().toString().matches("")||correo.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Completa todos los campos",Toast.LENGTH_SHORT).show();
        }else{
            if(validationsAreOkay()){
                makeHttpRequest();
                manageProgressDialog();
            }
        }
    }

    private void makeHttpRequest() {

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
            protected Map<String,String>getParams()throws AuthFailureError{
                HashMap<String,String>envio= new HashMap<>();
                envio.put("nombre",nombreUsuario.getText().toString());
                envio.put("correo",correo.getText().toString());
                envio.put("contra2",contrasena2.getText().toString());
                envio.put("contra",contrasena1.getText().toString());
                return  envio;

            }
        };
        requestQueue.add(stringRequest);


    }

    private void manageProgressDialog() {

        final ProgressDialog progressDialog = new ProgressDialog(RegistrarUsuario.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registrando...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        prepareNextActivity();
                        progressDialog.dismiss();
                    }
                }, 2000);

    }

    private void prepareNextActivity() {

        Intent irAListaPreguntas = new Intent(RegistrarUsuario.this,ListaPreguntas.class);
        irAListaPreguntas.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(irAListaPreguntas);
        finish();

    }

    private boolean validationsAreOkay() {

        boolean valueToReturn = false;
        if(userAlreadyExist()){
            Toast.makeText(getApplicationContext(),"El usuario que intentas registrar ya existe",Toast.LENGTH_LONG).show();
        }else {

            if(!contrasena1.getText().toString().matches(contrasena2.getText().toString())){
                Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
            }else{
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(correo.getText().toString()).matches()){
                    Toast.makeText(getApplicationContext(),"Formato de email incorrecto",Toast.LENGTH_SHORT).show();
                }else {
                    // Successfull
                    valueToReturn = true;
                }
            }
        }

        return valueToReturn;
    }

    private boolean userAlreadyExist() {

        boolean valueToReturn = false;
        // Validate there is an user registered with this alias
        if(nombreUsuario.getText().toString().equals("Jeff")){

            valueToReturn = true;
        }else {


            valueToReturn = false;
        }

        return  valueToReturn;
    }

}
