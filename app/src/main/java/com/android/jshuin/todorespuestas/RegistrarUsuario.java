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
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

                    Toast.makeText(getApplicationContext(),"El usuario ha sido registrado",Toast.LENGTH_SHORT).show();
                    //finish();
                    SharedPreferences datos = getSharedPreferences("datosusuario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = datos.edit();
                    editor.putString("correo","aplicacion@gmail.com");
                    editor.commit();
                    Intent irAListaPreguntas = new Intent(RegistrarUsuario.this,MainActivity.class);
                    irAListaPreguntas.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(irAListaPreguntas);
                    finish();
                }
        }
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
        }

        return  valueToReturn;
    }
}
