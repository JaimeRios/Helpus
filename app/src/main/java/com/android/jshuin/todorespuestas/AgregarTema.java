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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tema);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);{}
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
            Intent irAListaPreguntas = new Intent(AgregarTema.this,ListaPreguntas.class);
            irAListaPreguntas.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(irAListaPreguntas);
            finish();
        }

    }

}
