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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgregarRespuesta extends AppCompatActivity {

    int questionSelected;
    @BindView(R.id.ListaRestitulo)
    TextView titulo;
    @BindView(R.id.ListaResrespuesta)
    EditText nuevaRespuesta;
    @BindView(R.id.ListaRescorreo)
    EditText correo;
    @BindView(R.id.ListaReslistaRespuestas)
    ListView listaRespuestas;
    @BindView(R.id.ListaResAgregarRespuesta)
    Button AgregarResButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_respuesta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        viewSettings();
    }

    private void viewSettings() {

        questionSelected = getIntent().getIntExtra("questionSelected",0);
        titulo.setText("Tema de la pregunta");
        Toast.makeText(getApplicationContext(),"Pregunta seleccionada: "+questionSelected,Toast.LENGTH_SHORT).show();
        loadSharePreferences();
        loadAnswers();
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(AgregarRespuesta.this);
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
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(AgregarRespuesta.this);
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
            Toast.makeText(getApplicationContext(),"Tu respuesta fue registrada",Toast.LENGTH_SHORT).show();
            loadAnswers();
        }

    }

    public void loadAnswers(){

        List<String> arrayList = new ArrayList<String>();
        for (int index=0;index<20;index++){
            arrayList.add("Respuesta #"+index);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.text_in_cells,
                arrayList
        );
        listaRespuestas.setAdapter(arrayAdapter);

    }

    private void loadSharePreferences() {
        SharedPreferences datos = getSharedPreferences("datosusuario", Context.MODE_PRIVATE);
        correo.setText(datos.getString("correo",""));
        SharedPreferences.Editor editor = datos.edit();
        editor.putString("correo",correo.getText().toString());
        editor.commit();
    }

}
