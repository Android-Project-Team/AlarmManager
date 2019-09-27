package com.andstudy.alarmmanager.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.andstudy.alarmmanager.model.Defines;

public class DBHelper extends SQLiteOpenHelper {
    DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
 /*       String sqlStudent = "create table if not exists "+ Defines.ALARM +"(" +
                "_id integer primary key autoincrement," +
                "id text, " +
                "password text, " +
                "cellphone text);";*/
        // TABLE DROP

        //
        String sqlStudent = "create table if not exists "+ Defines.ALARM +"(" +
                "_id integer primary key autoincrement," +
                "enable text, " +
                "alarmDate text, " +
                "alarmTime text, " +
                "alarmCycle int, " +
                "alarmMaxCount int, " +
                "alarmNotice text, " +
                "dayCircle text, " +
                "alarmNote text, " +
                "fileName text, " +
                "vibeLevel int, " +
                "alarmCount int);";
        db.execSQL(sqlStudent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlStudent = "drop table if exists "+ Defines.ALARM +";";
        db.execSQL(sqlStudent);
        onCreate(db);
    }


}
