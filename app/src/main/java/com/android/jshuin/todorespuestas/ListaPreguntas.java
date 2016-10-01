package com.android.jshuin.todorespuestas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ListaPreguntas extends AppCompatActivity {

    @BindView(R.id.ListaPrelistaTemas)
    GridView listaPreguntas;
    ArrayList<String> preguntas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_preguntas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        traerDatos();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irAgregarTema = new Intent(ListaPreguntas.this,AgregarTema.class);
                startActivity(irAgregarTema);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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


    public void traerDatos(){
        ArrayList<String> arrayList = new ArrayList<>();
        preguntas.clear();
        arrayList.add("Como se hace un splash en android?");
        preguntas.add("Como se hace un splash en android?");
        arrayList.add(" => ");
        arrayList.add("Como creo una base de datos con sqlite en android?");
        preguntas.add("Como creo una base de datos con sqlite en android?");
        arrayList.add(" => ");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplication(),android.R.layout.simple_list_item_1,arrayList);
        listaPreguntas.setAdapter(arrayAdapter);

    }

    @OnItemClick(R.id.ListaPrelistaTemas)
    void onItemSelected(int position){
        // TODO ...
        int fila,columna;
        if(position%2==0){//columna 1
            fila=position/2+1;
        }else{//columna2
            fila=(position-1)/2+1;
            Intent irAgregarRespuesta = new Intent(ListaPreguntas.this,AgregarRespuesta.class);
            irAgregarRespuesta.putExtra("Tema",preguntas.get(fila-1));
            startActivity(irAgregarRespuesta);

        }
    }

}
