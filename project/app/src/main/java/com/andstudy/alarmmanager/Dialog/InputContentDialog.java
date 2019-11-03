package com.andstudy.alarmmanager.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andstudy.alarmmanager.R;
import com.andstudy.alarmmanager.util.MyDebug;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputContentDialog extends Dialog {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.contentText)
    EditText contentText;
    @BindView(R.id.cancleButton)
    Button cancleButton;
    @BindView(R.id.okButton)
    Button okButton;

    private String content;
    public InputContentDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_contents_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.okButton)
    public void okButton() {
        MyDebug.log("okButton");
        content = contentText.getText().toString();
        dismiss();
    }

    @OnClick(R.id.cancleButton)
    public void cancleButton() {
        MyDebug.log("cancleButton");
        dismiss();
    }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
