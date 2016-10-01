package com.android.jshuin.todorespuestas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
                Toast.makeText(getApplicationContext(),"sobre nosotros",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SobreAplicacion2:
                Toast.makeText(getApplicationContext(),"sobre la aplicacion ",Toast.LENGTH_SHORT).show();
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
