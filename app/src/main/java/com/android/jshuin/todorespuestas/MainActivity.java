package com.android.jshuin.todorespuestas;

import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.MainloginButtom)
    Button loginButtom;
    @BindView(R.id.MainregistrarButtom)
    Button registrarButtom;
    @BindView(R.id.Mainusuario)
    EditText nombreUsuario;
    @BindView(R.id.Maincontra)
    EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Toast.makeText(getApplicationContext(),"Por favor ngrese sus datos",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Ingresando",Toast.LENGTH_SHORT).show();
            Intent irAListaPreguntas = new Intent(MainActivity.this,ListaPreguntas.class);
            startActivity(irAListaPreguntas);

        }

    }


    @OnClick(R.id.MainregistrarButtom)
    public void registrar(){
        Intent irARegistrar = new Intent(MainActivity.this,RegistrarUsuario.class);
        startActivity(irARegistrar);
        Toast.makeText(getApplicationContext(),"volvio",Toast.LENGTH_SHORT).show();
    }

}
