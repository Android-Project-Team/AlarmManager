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
                context,
                Defines.DATABASE_NAME,
                null,
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

    public void delete(int id) {
        int result = 0;
        try {
            result  = sqLiteDatabase.delete(tableName, "_id=?", new String[]{String.valueOf(id)});
            MyDebug.log(tableName +" :"+ result + " row delete SUCCESS , id = " + id);
        }catch(SQLiteException e) {
            e.printStackTrace();
            MyDebug.log(tableName +" : " + result + "row delete FAILURE. id = " + id);
        }
    }

    public void delete(String alarmNote) {
        int result = 0;
        try {
            result  = sqLiteDatabase.delete(tableName, "alarmNote=?", new String[]{String.valueOf(alarmNote)});
            MyDebug.log(tableName +" :"+ result + " row delete SUCCESS , alarmNote = " + alarmNote);
        }catch(SQLiteException e) {
            e.printStackTrace();
            MyDebug.log(tableName +" : " + result + "row delete FAILURE. alarmNote = " + alarmNote);
        }
    }

    //public List<Alarm> viewAlarmList() {
    public ArrayList<Alarm> viewAlarmList() {
        Cursor c = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        // sqlLiteDatabase = getReadableDatabase
        ArrayList<Alarm> container = new ArrayList<>();
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
 /*
    public Alarm selectAlarm(int id) {
        Cursor c = sqLiteDatabase.query(tableName, null, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        // sqlLiteDatabase = getReadableDatabase
        Alarm tAlarm = new Alarm();
        if(c != null ) {
            c.moveToNext();
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
        }
        MyDebug.log("변경전 : " + tAlarm.getId());
        return tAlarm;
    }
*/
    public boolean selectEnable (int id) {
        Cursor c = sqLiteDatabase.query(tableName, null, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        // sqlLiteDatabase = getReadableDatabase
        Alarm tAlarm = new Alarm();
        tAlarm.setEnable((c.getInt(c.getColumnIndex("enable")) == 1));
        MyDebug.log("변경전 : " + tAlarm.getEnable());
        return tAlarm.getEnable();
    }

    public void updateEnable(int id) {
        ContentValues values = new ContentValues();
        boolean enable = (selectEnable(id)?false:true);
        MyDebug.log("변경후: " + enable);
        values.put("enable", enable);
        int result = sqLiteDatabase.update(tableName, // tableName
                values,    // 뭐라고 변경할지 ContentValues 설정
                "id=" +id, // 바꿀 항목을 찾을 조건절
                null);// 바꿀 항목으로 찾을 값 String 배열
        MyDebug.log(tableName + " updateEnable" + result + "번째 row update 성공했음");
    }

}
