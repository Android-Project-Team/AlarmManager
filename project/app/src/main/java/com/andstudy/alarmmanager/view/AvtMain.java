package com.andstudy.alarmmanager.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.andstudy.alarmmanager.R;
import com.andstudy.alarmmanager.adapter.ListAdapter;
import com.andstudy.alarmmanager.model.Alarm;
import com.andstudy.alarmmanager.model.AlarmManager;

import java.util.ArrayList;

public class AvtMain extends AppCompatActivity {
    ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
    ListView listview;//알람 list
    TextView listnulltext;//list가 하나도 없을때 표시되는 textview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avt_main);

        listview=(ListView)findViewById(R.id.listview);
        listnulltext=(TextView) findViewById(R.id.listnulltext);
        //객체 생성
        AlarmManager alarmManager=AlarmManager.getInstance();
        //alarmlist = alarmManager.GetAlarmList();


        //alram list length check
        if(alarmList==null){
            listview.setVisibility(View.GONE);
            listnulltext.setVisibility(View.VISIBLE);
        }else{
            listview.setVisibility(View.VISIBLE);
            listnulltext.setVisibility(View.GONE);

            Alarm alarm=new Alarm();
            alarm.setId(1);
            alarm.setAlarmNote("알람1");
            alarm.setAlarmDate("2019-09-23");
            alarm.setEnable(false);
            alarmList.add(alarm);
            Alarm alarm2=new Alarm();
            alarm2.setId(1);
            alarm2.setAlarmNote("알람1");
            alarm2.setDayCircle("0,0,0,0,0,0,0");
            alarm2.setEnable(false);
            alarmList.add(alarm2);

            ListAdapter adapter = new ListAdapter(alarmList) ;
            listview.setAdapter(adapter);



        }

    }
}
