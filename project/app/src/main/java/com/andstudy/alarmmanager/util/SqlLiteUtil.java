package com.andstudy.alarmmanager.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import java.util.ArrayList;
import java.util.List;

import com.andstudy.alarmmanager.model.Alarm;
import com.andstudy.alarmmanager.model.Defines;


public class SqlLiteUtil {
    private SQLiteDatabase sqLiteDatabase;
    private String tableName;

    private SqlLiteUtil() {}
    private static class SingleTon {
        public static final SqlLiteUtil Instance = new SqlLiteUtil();
    }
    public static SqlLiteUtil getInstance() {
        return SingleTon.Instance;
    }

    public void setInitView(Context context, String tableName) {
        this.tableName = tableName;

        DBHelper helper = new DBHelper(
                context,  // 현재 화면의 제어권자
                Defines.DATABASE_NAME,  // 데이터베이스 이름
                null, // 커서팩토리 - null 이면 표준 커서가 사용됨
                5);

        try {
            sqLiteDatabase = helper.getWritableDatabase();
            //sqLiteDatabase = helper.getReadableDatabase();

        } catch (SQLiteException e) {
            e.printStackTrace();
            MyDebug.log(tableName + " == > CAN'T OPEN DATABASE ");
        }
    }

    public void insert(Alarm alarm) {
        //MyDebug.log("===================================== Enter insert ===================================");
        ContentValues values = new ContentValues();
        // 키,값의 쌍으로 데이터 입력
        if(tableName.equals(Defines.ALARM)) {
            //MyDebug.log("===================================== Enter equals(Defines.ALARM) ===================================");
            //values.put("id", alarm.getId());
            values.put("enable", alarm.getEnable());
            values.put("alarmDate", alarm.getAlarmDate());
            values.put("alarmTime", alarm.getAlarmTime());
            values.put("alarmCycle", alarm.getAlarmCycle());
            values.put("alarmMaxCount", alarm.getAlarmMaxCount());
            values.put("alarmNotice", alarm.getAlarmNotice());
            values.put("dayCircle", alarm.getDayCircle());
            values.put("alarmNote", alarm.getAlarmNote());
            values.put("fileName", alarm.getFileName());
            values.put("vibeLevel", alarm.getVibeLevel());
            values.put("alarmCount", alarm.getAlarmCount());
        }

        long result = 0;
        try {
            result = sqLiteDatabase.insert(tableName, null, values);
            MyDebug.log(tableName+" : "+ result + "_ row insert SUCCESS");
        }catch(SQLiteException e) {
            e.printStackTrace();
            MyDebug.log(tableName+" : " + result + "_ row insert FAILURE.");
        }
    }

    public void delete(int number) {
        int result = 0;
        try {
            result  = sqLiteDatabase.delete(tableName, "_id=?", new String[]{String.valueOf(number)});
            MyDebug.log(tableName +" :"+ result + " row delete SUCCESS , id = " + number );
        }catch(SQLiteException e) {
            e.printStackTrace();
            MyDebug.log(tableName +" : " + result + "row delete FAILURE. id = " + number);
        }
    }

    public List<Alarm> viewAlarmList() { // alarm List 보기.
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        // sqlLiteDatabase = getReadableDatabase
        List<Alarm> container = new ArrayList<>();
        try {
            if (c!= null) {
                while (c.moveToNext()) {
                    Alarm tAlarm = new Alarm();
                    tAlarm.setId(c.getInt(c.getColumnIndex("_id")));
                    tAlarm.setEnable((c.getInt(c.getColumnIndex("enable")) == 1));
                    tAlarm.setAlarmDate(c.getString(c.getColumnIndex("alarmDate")));
                    tAlarm.setAlarmTime(c.getString(c.getColumnIndex("alarmTime")));
                    tAlarm.setAlarmCycle(c.getInt(c.getColumnIndex("alarmCycle")));
                    tAlarm.setAlarmMaxCount(c.getInt(c.getColumnIndex("alarmMaxCount")));
                    tAlarm.setAlarmNotice(c.getString(c.getColumnIndex("alarmNotice")));
                    tAlarm.setDayCircle(c.getString(c.getColumnIndex("dayCircle")));
                    tAlarm.setAlarmNote(c.getString(c.getColumnIndex("alarmNote")));
                    tAlarm.setFileName(c.getString(c.getColumnIndex("fileName")));
                    tAlarm.setVibeLevel(c.getInt(c.getColumnIndex("vibeLevel")));
                    tAlarm.setAlarmCount(c.getInt(c.getColumnIndex("alarmCount")));
                    container.add(tAlarm);
                    MyDebug.log(tableName + " : SELECT SUCCESS" );
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
            MyDebug.log(tableName + " : SELECT FAILURE" );

        }

        return container;
    }
}
