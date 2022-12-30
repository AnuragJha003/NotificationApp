package com.example.notificationtester;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private  int notificationid=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set onlcikc listener
        findViewById(R.id.setbtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);

    }

    @Override
    public  void onClick(View view){
        EditText editText=findViewById(R.id.editText);
        TimePicker timePicker=findViewById(R.id.timePicker);

        //set notification and text

        Intent intent=new Intent(MainActivity.this,AlarmReceiver.class);

        intent.putExtra("notificationId",notificationid);
        intent.putExtra("todo",editText.getText().toString());

        PendingIntent alarmIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent
        ,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm=(AlarmManager) getSystemService(ALARM_SERVICE);

        switch (view.getId()){
            case  R.id.setbtn:
                int hour=timePicker.getCurrentHour();
                int minute= timePicker.getCurrentMinute();

                //CREATE TIME

                Calendar startTime=Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY,hour);
                startTime.set(Calendar.MINUTE,minute);
                startTime.set(Calendar.SECOND,0);
                long alarmStartTime=startTime.getTimeInMillis();

                //SET ALARM
                //SET(TYPE.MILLI,SEC)

                alarm.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);

                Toast.makeText(this, "DONE!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "CANECELED!", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}