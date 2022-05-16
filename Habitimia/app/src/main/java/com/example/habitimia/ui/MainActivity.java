package com.example.habitimia.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.habitimia.R;
import com.example.habitimia.data.model.User;
import com.example.habitimia.ui.arena.ArenaFragment;
import com.example.habitimia.ui.home.HomeFragment;
import com.example.habitimia.util.MyNotification;
import com.example.habitimia.util.NotificationReceiver;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private android.hardware.SensorManager sensorManager;

    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    int sensor = Sensor.TYPE_ACCELEROMETER;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null)
            user = (User) intent.getSerializableExtra("user");
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationbar);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;
                switch (item.getItemId())
                {
                    case R.id.homeFragment : temp = new HomeFragment();
                        break;
                    case R.id.dailyFragment : temp = new DailyFragment();
                        break;
                    case R.id.questFragment : temp = new QuestFragment();
                        break;
                    case R.id.arenaFragment : temp = new ArenaFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,temp).commit();
                return true;
            }
        });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(sensorManager).registerListener(sensorListener, sensorManager.getDefaultSensor(sensor),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        createTimedNotification(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
        System.out.println(" PAUSED ");
    }
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(sensorListener);
        System.out.println(" STOPPED ");
    }
    @Override
    protected void onResume() {
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(sensor),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
        System.out.println(" RESUMED ");
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new QuestFragment()).commit();
                Toast.makeText(getApplicationContext(), "Shake event detected", Toast.LENGTH_SHORT).show();
                MyNotification.createNotification(MainActivity.this, getApplicationContext());
                MyNotification.createNotification(MainActivity.this, getApplicationContext());
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    public void createTimedNotification (Context context) { //(Activity activity, Context context)
        Intent myIntent = new Intent(context, NotificationReceiver. class ) ;
        AlarmManager alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE ) ;
        PendingIntent pendingIntent = PendingIntent. getService ( this, 0 , myIntent , 0 ) ;
        Calendar calendar = Calendar. getInstance () ;
//        calendar.set(Calendar. SECOND , 0 ) ;
//        calendar.set(Calendar. MINUTE , 0 ) ;
//        calendar.set(Calendar. HOUR , 0 ) ;
//        calendar.set(Calendar. AM_PM , Calendar. AM ) ;
//        calendar.add(Calendar. DAY_OF_MONTH , 1 ) ;
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String date = dateFormat.format(calendar.getTimeInMillis());
        alarmManager.setRepeating(AlarmManager. RTC_WAKEUP , calendar.getTimeInMillis() + (60 * 1000), 1000/*1000 * 60 * 60 * 24*/ , pendingIntent) ;

        boolean isWorking = (PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, PendingIntent.FLAG_NO_CREATE) != null);
        if (isWorking) {
        } else {}

    }
}