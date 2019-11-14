package com.andstudy.alarmmanager.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.andstudy.alarmmanager.R;
import com.andstudy.alarmmanager.model.Alarm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class ActSettingBell extends AppCompatActivity {

    @BindView(R.id.rbtnGroupContent)
    RadioGroup rbtnGroupContent;
    @BindView(R.id.bell1)
    RadioButton bell1;
    @BindView(R.id.bell2)
    RadioButton bell2;
    @BindView(R.id.bell3)
    RadioButton bell3;
    @BindView(R.id.vibeSeekBar)
    SeekBar vibeSeekBar;
    @BindView(R.id.setButton)
    Button setButton;

    String bell = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_alarm_bell);
        ButterKnife.bind(this);
        bell1.setChecked(true);
    }

    //--------------------------------------------------------------------------------------------//
    // set Button : Music and Vibe
    //--------------------------------------------------------------------------------------------//
    @OnClick(R.id.setButton)
    public void setBellAndVibe() {
        Intent outIntent = new Intent(getApplicationContext(), AvtSetting.class);
        //String vibe = String.valueOf(vibeSeekBar);
        int vibe = 0;
        outIntent.putExtra("vibe", vibe);
        outIntent.putExtra("bell", bell);
        setResult(RESULT_OK,outIntent);
        finish();
    }

   @OnClick({R.id.bell1, R.id.bell2, R.id.bell3})
   public void onRadioButtonClicked(RadioButton radioButton) {
       // Is the button now checked?
       boolean checked = radioButton.isChecked();

       // Check which radio button was clicked
       switch (radioButton.getId()) {
           case R.id.bell1 :
               bell = "1";
               break;
           case R.id.bell2 :
               bell = "2";
               break;
           case R.id.bell3 :
               bell = "3";
               break;
       }
   }
}
