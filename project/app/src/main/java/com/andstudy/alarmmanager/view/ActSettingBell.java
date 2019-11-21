package com.andstudy.alarmmanager.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.andstudy.alarmmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActSettingBell extends AppCompatActivity {


    String bell = "1";
    int vibe = 50;
    @BindView(R.id.bell1)
    RadioButton bell1;
    @BindView(R.id.bell2)
    RadioButton bell2;
    @BindView(R.id.bell3)
    RadioButton bell3;
    @BindView(R.id.rbtnGroupContent)
    RadioGroup rbtnGroupContent;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.seekBarText)
    TextView seekBarText;
    @BindView(R.id.linearLayout1)
    LinearLayout linearLayout1;
    @BindView(R.id.setButton)
    Button setButton;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_alarm_bell);
        ButterKnife.bind(this);
        bell1.setChecked(true);

        final SeekBar vibeSeekBar = (SeekBar) findViewById(R.id.vibeSeekBar);

        seekBarText.setText(vibeSeekBar.getProgress() + "/" + vibeSeekBar.getMax());

        vibeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                vibe = progressValue;
                seekBarText.setText(String.valueOf(vibe));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarText.setText(vibe + "/" + vibeSeekBar.getMax());
            }
        });
    }

    //--------------------------------------------------------------------------------------------//
    // set Button : Music and Vibe
    //--------------------------------------------------------------------------------------------//
    @OnClick(R.id.setButton)
    public void setBellAndVibe() {
        Intent outIntent = new Intent(getApplicationContext(), AvtSetting.class);
        //String vibe = String.valueOf(vibeSeekBar);
        outIntent.putExtra("vibe", vibe);
        outIntent.putExtra("bell", bell);
        setResult(RESULT_OK, outIntent);
        finish();
    }

    //--------------------------------------------------------------------------------------------//
    // 벨소리 선택
    //--------------------------------------------------------------------------------------------//
    @OnClick({R.id.bell1, R.id.bell2, R.id.bell3})
    public void onRadioButtonClicked(RadioButton radioButton) {
        // Is the button now checked?
//       boolean checked = radioButton.isChecked();

        // Check which radio button was clicked
        switch (radioButton.getId()) {
            case R.id.bell1:
                bell = "1";
                break;
            case R.id.bell2:
                bell = "2";
                break;
            case R.id.bell3:
                bell = "3";
                break;
        }
    }
    //--------------------------------------------------------------------------------------------//
    // 진동 크기 선택
    //--------------------------------------------------------------------------------------------//

}
