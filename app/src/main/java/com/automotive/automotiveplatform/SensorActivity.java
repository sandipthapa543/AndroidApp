package com.automotive.automotiveplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.automotive.automotiveplatform.ui.cart.NotificationsFragment;

public class SensorActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener listener;
    private TextView txtSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        txtSensor = findViewById(R.id.txtSensor);

        getValue();
    }

    private void getValue() {
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txtSensor.setText(
                                "X: "+ event.values[0] +"\n"+
                                "Y: "+ event.values[1] +"\n"+
                                "Z: "+ event.values[2] +"\n");

                if(( event.values[0]> 5 )){
                    Intent intent = new Intent(SensorActivity.this, DashboardActivity.class);
                    intent.putExtra("status", "open_map");
                    startActivity(intent);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if(sensor!= null){
            sensorManager
                    .registerListener(listener,sensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this,
                    "Requested sensor is not available",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void loadFragment() {
        Fragment fragment = new NotificationsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
