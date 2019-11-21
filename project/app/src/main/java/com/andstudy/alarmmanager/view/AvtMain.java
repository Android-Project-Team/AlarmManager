package com.andstudy.alarmmanager.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.andstudy.alarmmanager.R;
import com.andstudy.alarmmanager.adapter.ListAdapter;
import com.andstudy.alarmmanager.model.Alarm;
import com.andstudy.alarmmanager.model.AlarmManager;
import com.andstudy.alarmmanager.util.MyDebug;
import com.andstudy.alarmmanager.util.SqlLiteUtil;

import java.util.ArrayList;

public class AvtMain extends AppCompatActivity {
    ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
    ListView listview;//알람 list
    TextView listnulltext;//list가 하나도 없을때 표시되는 textview
    Button plusButton;
    View.OnClickListener viewListener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                //알람추가 이벤트
                case R.id.addbtn:
                    Intent intent = new Intent(getApplicationContext(),AvtSetting.class);
                    intent.putExtra("alarmId", -1);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avt_main);
        initDatabase();

        listview=(ListView)findViewById(R.id.listview);
        listnulltext=(TextView) findViewById(R.id.listnulltext);
        plusButton=(Button)findViewById(R.id.addbtn);
        plusButton.setOnClickListener(viewListener);

        //객체 생성
        AlarmManager alarmManager = AlarmManager.getInstance();

        //alram list length check
        if(!alarmManager.SetAlarmList()){
             MyDebug.log("alarmList IS NULL");
            listview.setVisibility(View.GONE);
            listnulltext.setVisibility(View.VISIBLE);
        }else{
            MyDebug.log("alarmList IS NOT NULL");

            listview.setVisibility(View.VISIBLE);
            listnulltext.setVisibility(View.GONE);

            alarmList = alarmManager.GetAlarmList();
            ListAdapter adapter = new ListAdapter(alarmList) ;
            listview.setAdapter(adapter);
            Log.d("test","test");
            //리스트 뷰 클릭 이벤트
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Alarm alarm = new Alarm();
                    Intent intent = new Intent(getApplicationContext(),AvtSetting.class);
                    intent.putExtra("alarmId", alarmList.get(position).getId());

                    startActivity(intent);
                }
            });
            listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { /* 삭제 */
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.d("test","longClick");
                    return true;
                }
            });

        }
    }
    /*
    * 오른쪽 상단에 메뉴 설정
    * res->menu 폴더생성
    * menuitem.xml 만들고 menuitem 생성
    * menuitem에 title에 바로 문자열을 입력을 하니 warning가 출력되어서
    * values->strings.xml 에 추가 후 이용
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    * 옵션메뉴 클릭 이벤트
    * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.settingButton){
            startActivity(new Intent(this,AvtSetting.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //--------------------------------------------------------------------------------------------//
    // DB 초기화
    //--------------------------------------------------------------------------------------------//
    private void initDatabase() {
        SqlLiteUtil.getInstance().setInitView(getApplicationContext(), "ALARM");
    }

    //--------------------------------------------------------------------------------------------//
    // DB : alarm insert
    //--------------------------------------------------------------------------------------------//
    private void insert(Alarm alarm) {
        // insert
        SqlLiteUtil.getInstance().insert(alarm);
        //     mTextView.append("INSERT : insert good \n");
    }

    //--------------------------------------------------------------------------------------------//
    // DB : alarm delete
    //--------------------------------------------------------------------------------------------//
    private void delete(int position) {
        SqlLiteUtil.getInstance().delete(position);
        //mTextView.setText("delete good \n");
    }
}
