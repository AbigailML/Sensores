package com.example.abi.sensores;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
//implementamos
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "MainActivity";
//   Creamos una variable de tipo privada sensorManager para poder hacer la comunicacion con nuestro dispositivo movil
    private SensorManager sensorManager;
//    utilizaremos sensor
    Sensor acelerometro, Giroscopio, Magneto, mLight, mPressure, mtemperature, mhumidity;

// Declaramos nuestros TwxtView
    TextView xValue, yValue, zValue;
    TextView xGiro, yGiro, zGiro;
    TextView xMagno, yMagno, zMagno;
    TextView Light, Pressure, Temperature, Humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        cremos nuestra instancia
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        Hacemos el casting a cada una de nuestras variables que son x,y,z
        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        xGiro = (TextView) findViewById(R.id.xGiroValue);
        yGiro = (TextView) findViewById(R.id.yGiroValue);
        zGiro = (TextView) findViewById(R.id.zGiroValue);

        xMagno = (TextView) findViewById(R.id.xMagnoValue);
        yMagno = (TextView) findViewById(R.id.yMagnoValue);
        zMagno = (TextView) findViewById(R.id.zMagnoValue);

        Light = (TextView) findViewById(R.id.Light);
        Pressure = (TextView) findViewById(R.id.Pressure);
        Temperature = (TextView) findViewById(R.id.Temperature);
        Humidity = (TextView) findViewById(R.id.Humidity);

//      Mandamos a llamar nuestra variable y que es de tipo acelerometro
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//      Ahora verificaremos si el dispositivo movil cuenta oon este sensor
        if (acelerometro != null) {
            sensorManager.registerListener(MainActivity.this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered acelerometro listener");
//      De lo contrario nos manda un mensaje de que nuestros dispositivo no lo soorta o no cuenta con ese sensor
        } else {
            xValue.setText("Acelerometro Not Supported");
            yValue.setText("Acelerometro Not Supported");
            zValue.setText("Acelerometro Not Supported");
        }
//      Mandamos a llamar nuestra variable y que es de tipo Giroscopio
        Giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//      Ahora verificaremos si el dispositivo movil cuenta oon este sensor
        if (Giroscopio != null) {
            sensorManager.registerListener(MainActivity.this, Giroscopio, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Giroscopio listener");
//      De lo contrario nos manda un mensaje de que nuestros dispositivo no lo soorta o no cuenta con ese sensor
        } else {
            xGiro.setText("Giro Not Supported");
            yGiro.setText("Giro Not Supported");
            zGiro.setText("Giro Not Supported");

        }
//      Mandamos a llamar nuestra variable y que es de tipo Magnetico
        Magneto = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//      Ahora verificaremos si el dispositivo movil cuenta oon este sensor
        if (Magneto != null) {
            sensorManager.registerListener(MainActivity.this, Magneto, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Magneto listener");
//      De lo contrario nos manda un mensaje de que nuestros dispositivo no lo soorta o no cuenta con ese sensor
        } else {
            xMagno.setText("Magno Not Supported");
            yMagno.setText("Magno Not Supported");
            zMagno.setText("Magno Not Supported");

        }
//      Mandamos a llamar nuestra variable y que es de tipo light
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
//      Ahora verificaremos si el dispositivo movil cuenta con este sensor
        if (Light != null) {
            sensorManager.registerListener(MainActivity.this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registrado Light listener");
//      De lo contrario nos manda un mensaje de que nuestros dispositivo no lo soorta o no cuenta con ese sensor
        } else {
            Light.setText("Ligth no Soportado");

        }
//      Mandamos a llamar nuestra variable y que es de tipo pressure
        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
//      Ahora verificaremos si el dispositivo movil cuenta con este sensor
        if (Pressure != null) {
            sensorManager.registerListener(MainActivity.this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registrado Pressure listener");
//      De lo contrario nos manda un mensaje de que nuestros dispositivo no lo soorta o no cuenta con ese sensor
        } else {
            Pressure.setText("Pressure no Soportado");

        }
//      Mandamos a llamar nuestra variable y que es de tipo temperature
        mtemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
//      Ahora verificaremos si el dispositivo movil cuenta con este sensor
        if (Temperature != null) {
            sensorManager.registerListener(MainActivity.this, mtemperature, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registrado Temperature listener");
//      De lo contrario nos manda un mensaje de que nuestros dispositivo no lo soorta o no cuenta con ese sensor
        } else {
            Pressure.setText("Temperature no Soportado");

        }
//      Mandamos a llamar nuestra variable y que es de tipo humidity
        mhumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
//      Ahora verificaremos si el dispositivo movil cuenta con este sensor
        if (Humidity != null) {
            sensorManager.registerListener(MainActivity.this, mhumidity, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registrado Humidity listener");
//       De lo contrario nos manda un mensaje de que nuestros dispositivo no lo soorta o no cuenta con ese sensor
        } else {
            Pressure.setText("Humidity no Soportado");

        }


    }


    @Override
    public void onSensorChanged(SensorEvent sensorEventevent) {
//  Esta clase representa de Sensor evento y contiene información como el tipo de sensor, que utilizaremos

        Sensor sensor = sensorEventevent.sensor;

//   Podria ser cualquiera de los sensores con los que cuenta nuestro dispositivo movil
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChange: X:" + sensorEventevent.values[0] + "Y:" + sensorEventevent.values[1] + "Z:" + sensorEventevent.values[2]);
//      Muestra los valores que estan en cada eje x,y,z
            xValue.setText("xValue: " + sensorEventevent.values[0]);
            yValue.setText("yValue: " + sensorEventevent.values[1]);
            zValue.setText("zValue: " + sensorEventevent.values[2]);

        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
//      Muestra los valores que estan en cada eje x,y,z
            xGiro.setText("xGValue: " + sensorEventevent.values[0]);
            yGiro.setText("yGValue: " + sensorEventevent.values[1]);
            zGiro.setText("zGValue: " + sensorEventevent.values[2]);

        } else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
//      Muestra los valores que estan en cada eje x,y,z
            xMagno.setText("xMValue: " + sensorEventevent.values[0]);
            yMagno.setText("yMValue: " + sensorEventevent.values[1]);
            zMagno.setText("zMValue: " + sensorEventevent.values[2]);

        } else if (sensor.getType() == Sensor.TYPE_LIGHT) {
//      Muestra los valores de la luz
            Light.setText("Light:" + sensorEventevent.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_PRESSURE) {
            Pressure.setText("Pressure:" + sensorEventevent.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
//      Muestra la temperatura ambiente  en grados Celsius.
            Temperature.setText("Temperature:" + sensorEventevent.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
//      Muestra el humedad relativa del aire ambiente
            Humidity.setText("Humidity:" + sensorEventevent.values[0]);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
