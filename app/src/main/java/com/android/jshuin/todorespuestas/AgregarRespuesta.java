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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgregarRespuesta extends AppCompatActivity {

    String Tema;
    @BindView(R.id.ListaRestitulo)
    TextView titulo;
    @BindView(R.id.ListaResrespuesta)
    EditText nuevaRespuesta;
    @BindView(R.id.ListaRescorreo)
    EditText correo;
    @BindView(R.id.ListaReslistaRespuestas)
    ListView listaRespuetas;
    @BindView(R.id.ListaResAgregarRespuesta)
    Button AgregarResButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_respuesta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tema = getIntent().getStringExtra("Tema");
        titulo.setText(Tema);
        traerRespuestas();
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
                Toast.makeText(getApplicationContext(),"sobre nosotros",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SobreAplicacion:
                Toast.makeText(getApplicationContext(),"sobre la aplicacion ",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cerrarSesion:
                Toast.makeText(getApplicationContext(),"cerrando sesion",Toast.LENGTH_SHORT).show();
                Intent irAMain = new Intent(AgregarRespuesta.this,MainActivity.class);
                irAMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(irAMain);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //metodo que llama el boton registrar respuesta
    @OnClick(R.id.ListaResAgregarRespuesta)
    public void registrarRespuesta(){
        if(nuevaRespuesta.getText().toString().matches("")||correo.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Por favor complete toda la informacion",Toast.LENGTH_SHORT).show();
        }else{
            ///ingrsese la informacion a base de datos
            Intent irAListaPreguntas = new Intent(AgregarRespuesta.this,ListaPreguntas.class);
            irAListaPreguntas.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(irAListaPreguntas);
            finish();
        }

    }

    public void traerRespuestas(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Eso es posible realizando lo siguiente"+" Respondio "+"Jefferson el 23 de Septeimbre 5:30");
        arrayList.add("Para realizar lo que deseas debes comenzar teniendo en cuenta tu objetivo"+" Respondio "+"Yeddy el 24 de Septeimbre 12:50");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplication(),android.R.layout.simple_list_item_1,arrayList);
        listaRespuetas.setAdapter(arrayAdapter);
    }

}
