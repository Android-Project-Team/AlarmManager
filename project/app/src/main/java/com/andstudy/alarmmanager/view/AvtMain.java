package com.andstudy.alarmmanager.view;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.andstudy.alarmmanager.R;
import com.andstudy.alarmmanager.model.Alarm;
import com.andstudy.alarmmanager.util.SqlLiteUtil;

import java.util.List;

public class AvtMain extends AppCompatActivity {
    private List<Alarm> alarms;
    private TextView mTextView;
    private Alarm alarm_insert_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avt_main);
        mTextView = (TextView) findViewById(R.id.testView);
        setInitView();


        alarm_insert_test = new Alarm();

/*        String str = "ID : " + alarm_insert_test.getId() + "|" + "\n" +
                "ENABLE : " + alarm_insert_test.getEnable() + "|" + "\n" +
                "ALARM DATE : " + alarm_insert_test.getAlarmDate() + "|" + "\n" +
                "ALARM TIME : " + alarm_insert_test.getAlarmTime() + "|" + "\n" +
                "ALARM CYCLE : " + alarm_insert_test.getAlarmCycle() + "|" + "\n" +
                "MAX COUNT : " + alarm_insert_test.getAlarmMaxCount() + "|" + "\n" +
                "ALARM NOTICE : " + alarm_insert_test.getAlarmNotice() + "|" + "\n" +
                "DAY CIRCLE : " + alarm_insert_test.getDayCircle() + "|" + "\n" +
                "ALARM NOTE : " + alarm_insert_test.getAlarmNote() + "|" + "\n" +
                "FILE NAME : " + alarm_insert_test.getFileName() + "|" + "\n" +
                "VIBE LEVEL : " + alarm_insert_test.getVibeLevel() + "|" + "\n" +
                "ALARM COUNT : " + alarm_insert_test.getAlarmCount() + "|" ;
        mTextView.append("SAMPLE DATA :\n"  + str + "\n");*/

        /* ********  TEST ******** */
        /* 1 * INSERT TEST * */
        //insert(alarm_insert_test);
        //select();
        /* 2 * DELETE TEST * */
        //delete(1);
        select();
        /* ********  TEST ******** */

    }

    private void setInitView() {
        SqlLiteUtil.getInstance().setInitView(getApplicationContext(), "ALARM");
    }

    // insertData
    private void insert(Alarm alarm) {
        // insert
        SqlLiteUtil.getInstance().insert(alarm);
   //     mTextView.append("INSERT : insert good \n");
    }

    // deleteData
    private void delete(int position) {
        SqlLiteUtil.getInstance().delete(position);
        //mTextView.setText("delete good \n");
    }

    private void select() {
        alarms = SqlLiteUtil.getInstance().viewAlarmList();
        String str = "";
        for (Alarm alarm : alarms) {
            str +=  "\nSELECT RESULT :\n" +
                    alarm.getId() + "|" +
                    alarm.getEnable() + "|" +
                    alarm.getAlarmDate() + "|" +
                    alarm.getAlarmTime() + "|" +
                    alarm.getAlarmCycle() + "|" +
                    alarm.getAlarmMaxCount() + "|" +
                    alarm.getAlarmNotice() + "|" +
                    alarm.getDayCircle() + "|" +
                    alarm.getAlarmNote() + "|" +
                    alarm.getFileName() + "|" +
                    alarm.getVibeLevel() + "|" +
                    alarm.getAlarmCount() + "|" ;
        }
        mTextView.setText( str );
    }
}

