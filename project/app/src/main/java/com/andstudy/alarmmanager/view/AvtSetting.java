package com.andstudy.alarmmanager.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andstudy.alarmmanager.R;
import com.andstudy.alarmmanager.model.Alarm;
import com.andstudy.alarmmanager.model.AlarmManager;
import com.andstudy.alarmmanager.util.MyDebug;

public class AvtSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avt_setting);
        MyDebug.log("On Setting Activity");
        Intent intent = getIntent();
        try{
            int alarmId = intent.getExtras().getInt("alarmId");
            Alarm alarm = null;
            if(alarmId >= 0) {
                alarm = AlarmManager.getInstance().GetAlarm(alarmId);

                //test data
                alarm = new Alarm();
                alarm.setAlarmDate("20191103");
            }

            initAlarmData(alarm);
        }
        catch (Exception e)
        {
            MyDebug.log(e.getMessage());
        }
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    private void initAlarmData(Alarm alarm)
    {
        if(alarm == null){
            alarm = makeNewAlarmData();
        }
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    private Alarm makeNewAlarmData()
    {
        Alarm newAlarm = new Alarm();
        //TODO Set up new alarm data
        return newAlarm;
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//

    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
}
