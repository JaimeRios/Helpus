package com.android.jshuin.todorespuestas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
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
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.MainloginButtom)
    Button loginButtom;
    @BindView(R.id.MainregistrarButtom)
    TextView registrarButtom;
    @BindView(R.id.Mainusuario)
    EditText nombreUsuario;
    @BindView(R.id.Maincontra)
    EditText contrasena;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    Boolean loginSuccessfull;

    private static final String URL= "http://172.17.2.20:8888/Clientesres/consultar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        requestQueue= Volley.newRequestQueue(this);
        loginSuccessfull= false;

        viewSettings();
    }

    private void viewSettings() {
        registrarButtom.setPaintFlags(registrarButtom.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        registrarButtom.setText("¿No tienes una cuenta?  Registrate");
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
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
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(MainActivity.this);
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

    @OnClick(R.id.MainloginButtom)
    public void logear(){
        if(nombreUsuario.getText().toString().matches("")||contrasena.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Ingresa tu usuario y contraseña",Toast.LENGTH_LONG).show();
        }else{

            makeHttpRequest();
            manageProgressDialog();
            /*if(loginSuccessfull){
                manageProgressDialog();
            }else {
                Toast.makeText(getApplicationContext(),"Usuario o Contraseña Incorrecta",Toast.LENGTH_LONG).show();
            }*/
        }
    }

    private void makeHttpRequest() {
        stringRequest= new StringRequest(Request.Method.POST, URL, new
                Response.Listener<String>(){
                    @Override
                    public void onResponse (String Response) {
                        try {
                            JSONObject jsonObject = new JSONObject(Response);

                            if (jsonObject.names().get(0).equals("logueado")) {
                                loginSuccessfull=true;
                                String correousu = jsonObject.getString("logueado");
                                SharedPreferences guardar =getSharedPreferences("datosusuario", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = guardar.edit();
                                editor.putString("correo", correousu);
                                editor.commit();
                                //Toast.makeText(getApplicationContext(), "Bienvenido" + correousu, Toast.LENGTH_LONG).show();
                                //startActivity(new Intent(getApplicationContext(),ListaPreguntas.class));
                            }
                            else{
                                loginSuccessfull=false;
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
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                HashMap<String,String> envio= new HashMap<>();
                envio.put("usuario",nombreUsuario.getText().toString());
                envio.put("contra",contrasena.getText().toString());
                return  envio;

            }
        };
        requestQueue.add(stringRequest);
    }


    @OnClick(R.id.MainregistrarButtom)
    public void registrar(){
        Intent irARegistrar = new Intent(MainActivity.this,RegistrarUsuario.class);
        startActivity(irARegistrar);
    }

    private void manageProgressDialog() {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Iniciando...");
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

        Intent irAListaPreguntas = new Intent(MainActivity.this,ListaPreguntas.class);
        startActivity(irAListaPreguntas);

        /*SharedPreferences datos = getSharedPreferences("datosusuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datos.edit();
        editor.putString("correo","aplicacion@gmail.com");
        editor.commit();*/
    }

}
