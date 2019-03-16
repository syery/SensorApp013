package com.example.sensorapp01;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    CanvasView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        cv = new CanvasView(this,null);
        setContentView(cv);

        //端末センサーリストを表示
        SensorManager sm = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s: sensors){
            Log.d("SENSOR","名前は" + s.getName());
        }

        //加速度センサーを設定
        Sensor ac = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,ac,SensorManager.SENSOR_DELAY_NORMAL);
        
    }

    @Override
    public void onSensorChanged(SensorEvent sev){
        Log.d("SENSOR","Xは" + sev.values[0] + "Yは" + sev.values[1] + "Zは" + sev.values[2]);
        cv. setPos(sev.values[0],sev.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor s, int ac){

    }


}
