package com.andstudy.alarmmanager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.andstudy.alarmmanager.R;
import com.andstudy.alarmmanager.model.Alarm;
import com.andstudy.alarmmanager.util.MyDebug;

import java.util.ArrayList;

//리스트 뷰  adapter
public class ListAdapter extends BaseAdapter{
    private ArrayList<Alarm> AlramList=null;
    LayoutInflater inflater = null;

    public ListAdapter(ArrayList<Alarm> AlramList) {
        this.AlramList=AlramList;
    }

    @Override
    public int getCount() {
        return AlramList.size();
    }

    @Override
    public Object getItem(int position) {
        return AlramList.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Alarm id
        final int idx=AlramList.get(position).getId();
        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (inflater == null)
            {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }
        TextView note = (TextView) convertView.findViewById(R.id.noteid);
        TextView daycirle = (TextView) convertView.findViewById(R.id.daycircleid);
        final Switch enableid=(Switch)convertView.findViewById(R.id.enableid);

        note.setText(AlramList.get(position).getAlarmNote());
        daycirle.setText(AlramList.get(position).getDayCircle());
        //스위치 버튼 클릭 이벤트
        /*
        * click이나 change 이벤트를 안쓰는 이유는
        * listview item에서 switch를 이용을 하니 item 클릭이벤트가 안먹히고 switch를 뺄수가 없으니 touch 이벤트로 활용함
        * */
        enableid.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("ontouchEvent","enableid.isChecked() : "+enableid.isChecked());
                        break;
                }
                return false;
            }
        });
/*
        enableid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MyDebug.log("오나 : "+idx);
            }
        });
*/

        return convertView;
    }



}
