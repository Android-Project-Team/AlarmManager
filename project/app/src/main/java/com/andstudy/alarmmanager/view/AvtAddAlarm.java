package com.andstudy.alarmmanager.view;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.andstudy.alarmmanager.Dialog.DateDialogFragment;
import com.andstudy.alarmmanager.Dialog.InputContentDialog;
import com.andstudy.alarmmanager.Dialog.TimeDialogFragment;
import com.andstudy.alarmmanager.R;
import com.andstudy.alarmmanager.model.Alarm;
import com.andstudy.alarmmanager.util.MyDebug;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/* AvtMain에서 +버튼 클릭 시 intent로 호출,
 ** 여기서 반환 값 : Alarm 객체.
 ** main 에서 반환 값 처리 : query 처리
 */
public class AvtAddAlarm extends AppCompatActivity {

    Alarm alarm = new Alarm();
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.dateText)
    TextView dateText;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.timeText)
    TextView timeText;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.recycleText)
    TextView recycleText;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.checkBoxSun)
    CheckBox checkBoxSun;
    @BindView(R.id.checkBoxMon)
    CheckBox checkBoxMon;
    @BindView(R.id.checkBoxTue)
    CheckBox checkBoxTue;
    @BindView(R.id.checkBoxWed)
    CheckBox checkBoxWed;
    @BindView(R.id.checkBoxTur)
    CheckBox checkBoxTur;
    @BindView(R.id.checkBoxFri)
    CheckBox checkBoxFri;
    @BindView(R.id.checkBoxSat)
    CheckBox checkBoxSat;
    @BindView(R.id.textView14)
    TextView textView14;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.contentText)
    TextView contentText;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.musicVibeText)
    TextView musicVibeText;
    @BindView(R.id.addButton)
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //날짜
        // 시간
        // 다시울림
        // 요일반복
        // 알림내용
        // 알림음, 진동

        setContentView(R.layout.activity_add_alarm);
        ButterKnife.bind(this);
    }

    // 날짜 설정 dialog
    @OnClick(R.id.dateText)
    public void setDateButton() {
        DialogFragment newFragment = new DateDialogFragment();
        newFragment.show(getFragmentManager(),"DatePicker");
    }

    // 시간 설정 dialog
    @OnClick(R.id.timeText)
    public void setTimeButton(){
        DialogFragment newFragment = new TimeDialogFragment();
        newFragment.show(getFragmentManager(),"TimePicker");
    }

    // 다시울림 -알림 간격/반복/알림
    @OnClick(R.id.recycleText)
    public void setRecycleButton() {

    }

    // 알림음 선택 및 진동크기 - 화면전환(프래그먼트?)
    @OnClick(R.id.musicVibeText)
    public void setMusicVibeButton() {

    }

    // cancle Button
    @OnClick(R.id.contentText)
    public void addContentButton() {
        final InputContentDialog  contentDialog = new InputContentDialog(this);
        contentDialog.show();
        contentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                String content = contentDialog.getContent();
                contentText.setText(content);
            }
        });
    }

    // 임의 테스트..
    @OnClick(R.id.addButton)
    public void addAlarmButton() {
        /*
        this.enable = true;
        this.alarmDate = "00000000";
        this.alarmTime = alarmTime;
        this.alarmCycle = 3; // 3분 주기
        this.alarmMaxCount = 3; // 3번 반복
        this.alarmNotice = "0"; // 알림X
        this.dayCircle = dayCircle;
        this.alarmNote = alarmNote;
        this.fileName = "ALARM1";
        this.vibeLevel = 50;
        this.alarmCount = 0;
        */

        //alarm.setAlarmDate(); // 날짜
        //alarm.setAlarmTime(); // 시간
        //alarm.setAlarmCycle(); // 알람주기
        //alarm.setAlarmMaxCount(); // 반복횟수
        //alarm.setAlarmNotice(); // 알림(하루,3일,일주일)
        //alarm.setDayCircle();//알람요일
        //alarm.setAlarmNote();// 알람내용
        //alarm.setFileName(); // 알람제목
        //alarm.setAlarmCount(); // 현재 알람울린 횟수
        MyDebug.log("Button Click");
    }

    // checkbox 값 반환
    public String getStrigCycle(){
        String cycle = "";
        cycle +=  checkBoxSun.isChecked() ? "1" : "0";
        cycle += checkBoxMon.isChecked() ? "1" : "0";
        cycle += checkBoxTue.isChecked() ? "1" : "0";
        cycle += checkBoxWed.isChecked() ? "1" : "0";
        cycle += checkBoxTur.isChecked() ? "1" : "0";
        cycle += checkBoxFri.isChecked() ? "1" : "0";
        cycle += checkBoxSat.isChecked() ? "1" : "0";

        MyDebug.log("getStringCyle : " + cycle);

        return cycle;
    }
}
