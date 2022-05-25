package com.example.habitimia.ui;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.view.View;
import android.widget.Toast;

import com.example.habitimia.R;
import com.example.habitimia.data.model.AdventurerClass;
import com.example.habitimia.data.model.Guild;
import com.example.habitimia.data.model.Statistics;
import com.example.habitimia.data.model.User;
import com.example.habitimia.ui.arena.ArenaFragment;
import com.example.habitimia.ui.home.HomeFragment;
import com.example.habitimia.ui.login.LoginActivity;
import com.example.habitimia.ui.quest.CreateQuestFragment;
import com.example.habitimia.ui.quest.DailyFragment;
import com.example.habitimia.ui.quest.QuestFragment;
import com.example.habitimia.util.MyNotification;
import com.example.habitimia.util.NotificationReceiver;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ConstraintLayout Root;
    private FloatingActionButton FAB;
    private android.hardware.SensorManager sensorManager;

    private Fragment mOldFragment = null;
    private Fragment mCurrentFragment = new HomeFragment();
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    int sensor = Sensor.TYPE_ACCELEROMETER;

    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null)
            user = (User) intent.getSerializableExtra("user");
        setContentView(R.layout.activity_main);

        user = new User();
        user.setId(1L);
        user.setUsername("Mia");
        user.setStatistics(new Statistics());
        user.getStatistics().setAdventurerClass(AdventurerClass.A);
        user.getStatistics().setHP(10L);
        user.getStatistics().setExperience(0L);

        ArrayList<User> guildMembers = new ArrayList<User>();
        guildMembers.add(user);
        guildMembers.add(new User());
        user.setGuild(new Guild(guildMembers));

        Root = (ConstraintLayout) findViewById(R.id.MainRoot);
        FAB = (FloatingActionButton) findViewById(R.id.fab);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationbar);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentFragment instanceof QuestFragment) {
                    mCurrentFragment = new CreateQuestFragment();
                } else if(mCurrentFragment instanceof HomeFragment) {
                    // Déconnexion
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                LoadFragment();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mCurrentFragment = null;
                switch (item.getItemId())
                {
                    case R.id.homeFragment : mCurrentFragment = new HomeFragment();
                        break;
                    case R.id.dailyFragment : mCurrentFragment = new DailyFragment();
                        break;
                    case R.id.questFragment : mCurrentFragment = new QuestFragment();
                        break;
                    case R.id.arenaFragment : mCurrentFragment = new ArenaFragment();
                }
                LoadFragment();
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

        LoadFragment();
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

    public void setBackgroundColor(@ColorInt int color) {
        Root.setBackgroundColor(color);
    }

    public void setCurrentFragment(Fragment fragment) {
        mCurrentFragment = fragment;
    }

    public void LoadFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, mCurrentFragment).commit();
    }

    public void LoadFragment(Fragment fragment) {
        mOldFragment = mCurrentFragment;
        mCurrentFragment = fragment;
        LoadFragment();
    }

    public void UpdateFABIcon(int resId) {
        FAB.setImageResource(resId);
    }
}