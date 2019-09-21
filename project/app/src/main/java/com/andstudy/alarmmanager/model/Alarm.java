package com.andstudy.alarmmanager.model;

// Created by Sim on 2019-09-17.
// 알람에 필요한 데이터를 객체화 한다

public class Alarm {
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    //Parameters, don't reference parameters directly, use setter and getter
    private int id = -1;
    private boolean enable = true;
    private String alarmDate = ""; //YYYYMMDD
    private String alarmTime = ""; //hhmmss
    private String duty = "";  //간격 : 반복 : 알림 ex) "3 : 3 : 3,7"
    private String dayCircle = ""; //0,1,0,1,0,1,0 일,월,화,수,목,금,토  0 : 비활성 1: 활성
    private String alarmNote = ""; // 알람내용
    private String fileName = ""; // 파일제목, 알람음악
    private int vibeLevle = 50; //range 0 ~ 100, 진동세기, 10 단계로 나눌 것
    private int alarmCount = 0; // 알람울린횟수
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    public Alarm()
    {
        //TODO make init, default setting
        id = 1;
        enable = true;
        alarmDate = "19990101";
        alarmTime = "000000";
        duty = "3:3:3,7";
        dayCircle = "0000000";
        alarmNote = "ALARM CONTENTS";
        fileName = "MUSIC NAME";
        vibeLevle = 50;
        alarmCount = 0;
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    //TODO make setter getter
    public int getId(){
        return this.id;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getAlarmDate() {
        return alarmDate;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public String getDuty() {
        return duty;
    }

    public String getDayCircle() {
        return dayCircle;
    }

    public String getAlarmNote() {
        return alarmNote;
    }

    public String getFileName() {
        return fileName;
    }

    public int getVibeLevle() {
        return vibeLevle;
    }

    public int getAlarmCount() {
        return alarmCount;
    }

    //--------------------------------------------------------------------------------------------//
    public void setId(int id){
        this.id = id;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setAlarmDate(String alarmDate) {
        this.alarmDate = alarmDate;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public void setDayCircle(String dayCircle) {
        this.dayCircle = dayCircle;
    }

    public void setAlarmNote(String alarmNote) {
        this.alarmNote = alarmNote;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setVibeLevle(int vibeLevle) {
        this.vibeLevle = vibeLevle;
    }

    public void setAlarmCount(int alarmCount) {
        this.alarmCount = alarmCount;
    }
//--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//

}
