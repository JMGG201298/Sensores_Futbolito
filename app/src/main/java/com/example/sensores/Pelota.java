package com.example.sensores;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Pelota extends View implements SensorEventListener {
    Paint pincel=new Paint();
    int alto, ancho;
    int tamanio=30;
    int borde=10;
    float ejeX=0, ejeY=0, ejeZ1=0, ejeZ=0;
    String x,y,z;
    public Pelota(Context context) {
        super(context);
        SensorManager sensorManager=(SensorManager)getContext().getSystemService(context.SENSOR_SERVICE);
        Sensor sensorRotacion=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,sensorRotacion, SensorManager.SENSOR_DELAY_FASTEST);
        Display pantalla=((WindowManager)getContext().getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
        alto=pantalla.getHeight();
        ancho=pantalla.getWidth();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ejeX-=event.values[0];
        x=Float.toString(ejeX);
        if(ejeX<(tamanio+borde)){
            ejeX=(tamanio+borde);
        }else if(ejeX>(ancho-(tamanio+borde))){
            ejeX=ancho-(tamanio+borde);
        }
        ejeY+=event.values[1];
        y=Float.toString(ejeY);
        if(ejeY<(tamanio+borde)){
            ejeY=tamanio+borde;
        }else if(ejeY>(alto-tamanio-120)){
            ejeY=alto-tamanio-120;
        }
        ejeZ=event.values[2];
        z=String.format("%.2f",ejeZ);
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pincel.setColor(Color.BLUE);
        canvas.drawCircle(ejeX,ejeY,ejeZ+tamanio,pincel);
        pincel.setColor(Color.WHITE);

    }

}
