package com.android.jshuin.todorespuestas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.image)ImageView imagen;
    private CountDownTimer miTimer;
    Matrix matrix;
    float valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        matrix =new Matrix();
        valor=0.0f;
        Thread tiempo = new Thread(){
            public void run(){
                try{
                    sleep(3600);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        tiempo.start();
        comenzar();
    }

    private void comenzar(){
        miTimer = new CountDownTimer(3600,50) {
            @Override
            public void onTick(long l) {
                valor=valor+5.0f;
                matrix.postRotate(valor);
                Bitmap original = BitmapFactory.decodeResource(getResources(),R.drawable.signopregunta);
                Bitmap rotateBitmap = Bitmap.createBitmap(original,0,0,original.getWidth(),original.getHeight(),matrix,true);
                imagen.setImageBitmap(rotateBitmap);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
