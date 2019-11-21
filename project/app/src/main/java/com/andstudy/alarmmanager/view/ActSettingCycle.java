package com.andstudy.alarmmanager.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.andstudy.alarmmanager.R;
import com.andstudy.alarmmanager.util.MyDebug;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActSettingCycle extends AppCompatActivity {

    int cycle = 0;
    int count = 0;
    String notice = "";
    @BindView(R.id.cycle1)
    RadioButton cycle1;
    @BindView(R.id.cycle2)
    RadioButton cycle2;
    @BindView(R.id.cycle3)
    RadioButton cycle3;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.count1)
    RadioButton count1;
    @BindView(R.id.count2)
    RadioButton count2;
    @BindView(R.id.count3)
    RadioButton count3;
    @BindView(R.id.linearLayout1)
    LinearLayout linearLayout1;
    @BindView(R.id.notice1)
    CheckBox notice1;
    @BindView(R.id.notice2)
    CheckBox notice2;
    @BindView(R.id.notice3)
    CheckBox notice3;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.setButton)
    Button setButton;
    @BindView(R.id.linearLayout3)
    LinearLayout linearLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyDebug.log("ActSeeting Cycle 진입==");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seting_cycle);
        ButterKnife.bind(this);
        cycle1.setChecked(true);
        count1.setChecked(true);
    }

    //--------------------------------------------------------------------------------------------//
    // set Button : cycle, count, notice
    //--------------------------------------------------------------------------------------------//
    @OnClick(R.id.setButton)
    public void setBellAndVibe() {
        Intent outIntent = new Intent(getApplicationContext(), AvtSetting.class);

        setNotices();

        outIntent.putExtra("cycle", cycle);
        outIntent.putExtra("count", count);
        outIntent.putExtra("notice", notice);
        MyDebug.log(cycle + "||" + count + "||" + notice);
        setResult(RESULT_OK, outIntent);
        finish();
    }

    //--------------------------------------------------------------------------------------------//
    // 알림 간격 선택
    //--------------------------------------------------------------------------------------------//
    @OnClick({R.id.cycle1, R.id.cycle2, R.id.cycle3})
    public void onCycleRadioButtonClicked(RadioButton radioButton) {
        // Is the button now checked?
        boolean checked = radioButton.isChecked();

        // Check which radio button was clicked
        switch (radioButton.getId()) {
            case R.id.cycle1:
                cycle = 3;
                break;
            case R.id.cycle2:
                cycle = 5;
                break;
            case R.id.cycle3:
                cycle = 10;
                break;
        }
    }

    //--------------------------------------------------------------------------------------------//
    // 알림 반복 횟수 선택
    //--------------------------------------------------------------------------------------------//
    @OnClick({R.id.count1, R.id.count2, R.id.count3})
    public void onCountRadioButtonClicked(RadioButton radioButton) {
        // Is the button now checked?
        //boolean checked = radioButton.isChecked();
        // Check which radio button was clicked
        switch (radioButton.getId()) {
            case R.id.count1:
                count = 3;
                break;
            case R.id.count2:
                count = 5;
                break;
            case R.id.count3:
                count = 10;
                break;
        }
    }

    //--------------------------------------------------------------------------------------------//
    // 알림일 반환
    //--------------------------------------------------------------------------------------------//
    public void setNotices() {
        String notices = "";
        if (notice1.isChecked()) notices += "1";
        else notices += "0";
        notices += ",";
        if (notice2.isChecked()) notices += "3";
        else notices += "0";
        notices += ",";
        if (notice3.isChecked()) notices += "7";
        else notices += "0";
        notice = notices;
    }


}
