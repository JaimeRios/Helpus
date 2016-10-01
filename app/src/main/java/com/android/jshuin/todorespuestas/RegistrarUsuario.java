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
                Toast.makeText(getApplicationContext(),"sobre nosotros",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SobreAplicacion2:
                Toast.makeText(getApplicationContext(),"sobre la aplicacion ",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.RegistrarButtom)
    public void registrar(){
        if(nombreUsuario.getText().toString().matches("")||contrasena1.getText().toString().matches("")||
                contrasena2.getText().toString().matches("")||correo.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Por favor ingrese todos los datos",Toast.LENGTH_SHORT).show();
        }else{
            if(contrasena1.getText().toString().matches(contrasena2.getText().toString())){
                Toast.makeText(getApplicationContext(),"Registrando usuario",Toast.LENGTH_SHORT).show();
                //finish();
                Intent irAListaPreguntas = new Intent(RegistrarUsuario.this,ListaPreguntas.class);
                irAListaPreguntas.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(irAListaPreguntas);
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
