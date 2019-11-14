package com.andstudy.alarmmanager.view;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andstudy.alarmmanager.Dialog.DateDialogFragment;
import com.andstudy.alarmmanager.Dialog.InputContentDialog;
import com.andstudy.alarmmanager.Dialog.TimeDialogFragment;
import com.andstudy.alarmmanager.R;
import com.andstudy.alarmmanager.model.Alarm;
import com.andstudy.alarmmanager.model.AlarmManager;
import com.andstudy.alarmmanager.util.MyDebug;
import com.andstudy.alarmmanager.util.SqlLiteUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/* AvtMain에서 +버튼 클릭 시 intent로 호출,
 ** 여기서 반환 값 : Alarm 객체.
 ** main 에서 반환 값 처리 : query 처리
 */
public class AvtSetting extends AppCompatActivity {

    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.txtTime)
    TextView txtTime;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.txtCycle)
    TextView txtCycle;
    @BindView(R.id.linearLayout3)
    LinearLayout linearLayout3;
    @BindView(R.id.btnSun)
    CheckBox btnSun;
    @BindView(R.id.btnMon)
    CheckBox btnMon;
    @BindView(R.id.btnTue)
    CheckBox btnTue;
    @BindView(R.id.btnWed)
    CheckBox btnWed;
    @BindView(R.id.btnThu)
    CheckBox btnThu;
    @BindView(R.id.btnFri)
    CheckBox btnFri;
    @BindView(R.id.btnSat)
    CheckBox btnSat;
    @BindView(R.id.linearLayout4)
    LinearLayout linearLayout4;
    @BindView(R.id.linearLayout5)
    LinearLayout linearLayout5;
    @BindView(R.id.txtNote)
    TextView txtNote;
    @BindView(R.id.txtContent)
    TextView txtContent;
    @BindView(R.id.linearLayout6)
    LinearLayout linearLayout6;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnSave)
    Button btnSave;
    //날짜
    // 시간
    // 다시울림
    // 요일반복
    // 알림내용
    // 알림음, 진동
    Alarm alarm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avt_setting);
        ButterKnife.bind(this);

        MyDebug.log("On Setting Activity");
        Intent intent = getIntent();
        try{
            int alarmId = intent.getExtras().getInt("alarmId");
            if(alarmId >= 0) {
                alarm = AlarmManager.getInstance().GetAlarm(alarmId);
                //test data
                alarm = new Alarm();
                alarm.setAlarmDate("20191103");
            }
            initAlarmData(alarm);
            txtDate.setText("20191111");
        }
        catch (Exception e)
        {
            MyDebug.log(e.getMessage());
        }
    }

    //--------------------------------------------------------------------------------------------//
    // 날짜 설정
    //--------------------------------------------------------------------------------------------//
    @OnClick(R.id.linearLayout)
    public void setDateButton() {
        DialogFragment newFragment = new DateDialogFragment();
        newFragment.show(getFragmentManager(), "DatePicker");
    }

    //--------------------------------------------------------------------------------------------//
    // 시간 설정
    //--------------------------------------------------------------------------------------------//
    @OnClick(R.id.linearLayout2)
    public void setTimeButton() {
        DialogFragment newFragment = new TimeDialogFragment();
        newFragment.show(getFragmentManager(), "TimePicker");
    }

    //--------------------------------------------------------------------------------------------//
    // 알림 간격/반복/알림
    //--------------------------------------------------------------------------------------------//
    @OnClick(R.id.linearLayout3)
    public void setRecycleButton() {
    }

    //--------------------------------------------------------------------------------------------//
    // 알림내용 입력
    //--------------------------------------------------------------------------------------------//
    @OnClick(R.id.linearLayout5)
    public void setContentButton() {
        final InputContentDialog contentDialog = new InputContentDialog(this);
        contentDialog.show();
        contentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                String content = contentDialog.getContent();
                //contentText.setText(content);
            }
        });
    }

    //--------------------------------------------------------------------------------------------//
    // 알림음 선택 및 진동크기
    //--------------------------------------------------------------------------------------------//
    @OnClick(R.id.linearLayout6)
    public void setBellAndVibeButton() {
        Intent intent = new Intent(getApplicationContext(),ActSettingBell.class);
        intent.putExtra("vibe", "null");
        intent.putExtra("bell", "null");
        startActivityForResult(intent, 0);
    }

    //--------------------------------------------------------------------------------------------//
    // 취소(뒤로가기)
    //--------------------------------------------------------------------------------------------//
    @OnClick(R.id.btnCancel)
    public void cancleButton() {
        Intent intent = new Intent(getApplicationContext(),AvtMain.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //--------------------------------------------------------------------------------------------//
    // 알람 저장 : 예외 처리 필요
    //--------------------------------------------------------------------------------------------//
    @OnClick(R.id.btnSave)
    public void saveButton() {
        Intent intent = new Intent(getApplicationContext(),AvtMain.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Alarm alarm = new Alarm();
        alarm.setAlarmNote("ok?");
        insert(alarm);

        startActivity(intent);
    }

    //--------------------------------------------------------------------------------------------//
    // Alarm day 값 반환
    //--------------------------------------------------------------------------------------------//
    public String getStrigDayCycle() {
        String cycle = "";
        cycle += btnSun.isChecked() ? "1" : "0";
        cycle += btnMon.isChecked() ? "1" : "0";
        cycle += btnTue.isChecked() ? "1" : "0";
        cycle += btnWed.isChecked() ? "1" : "0";
        cycle += btnThu.isChecked() ? "1" : "0";
        cycle += btnFri.isChecked() ? "1" : "0";
        cycle += btnSat.isChecked() ? "1" : "0";
        return cycle;
    }

    //--------------------------------------------------------------------------------------------//
    // filename 값 반환
    //--------------------------------------------------------------------------------------------//
    public String splitContent(String contentName){
        String[] temp = txtContent.getText().toString().split(",");
        switch(contentName) {
            case "filename" :
                return temp[0];
            case "vibe" :
                return temp[1];
        }
        return null;
    }

    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    private void initAlarmData(Alarm alarm)
    {
        if(alarm == null){
            alarm = makeNewAlarmData();
        }
        setAlarmToView(alarm);
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
    // TEST
    //--------------------------------------------------------------------------------------------//
    private void setAlarmToView(Alarm alarm){
        txtDate.setText(alarm.getAlarmDate());
        txtTime.setText(alarm.getAlarmTime());
        txtNote.setText(alarm.getAlarmNote());
    }

    //--------------------------------------------------------------------------------------------//
    // DB : insert Alarm
    //--------------------------------------------------------------------------------------------//
    private void insert(Alarm alarm){
        SqlLiteUtil.getInstance().insert(alarm);
        //     mTextView.append("INSERT : insert good \n");
    }

    //--------------------------------------------------------------------------------------------//
    // setting된 Alarm 반환
    //--------------------------------------------------------------------------------------------//
    private Alarm getAlarm() {
        Alarm alarm = new Alarm();

        alarm.setEnable(true);
        alarm.setAlarmDate(txtDate.getText().toString());
        alarm.setAlarmTime(txtTime.getText().toString());
        //alarm.setAlarmCycle(); // 3분 주기
        //alarm.alarmMaxCount = 3; // 3번 반복
        //alarm.alarmNotice = "0"; // 알림X
        alarm.setDayCircle(getStrigDayCycle());
        alarm.setAlarmNote(txtNote.getText().toString());
        alarm.setFileName(splitContent("filename"));
        alarm.setVibeLevel(Integer.parseInt(splitContent("vibe")));
        alarm.setAlarmCount(0);

        return alarm;
    }

    //--------------------------------------------------------------------------------------------//
    // + Intent 수신 +
    //--------------------------------------------------------------------------------------------//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //alarm.setVibeLevel(data.getIntExtra("vibe", 0));
            //alarm.setFileName(data.getStringExtra("bell"));
            txtContent.setText(data.getStringExtra("bell") + "," + data.getIntExtra("vibe", 0));
        }
    }
}
