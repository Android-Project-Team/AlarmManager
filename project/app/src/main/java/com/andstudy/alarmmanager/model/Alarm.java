package com.andstudy.alarmmanager.model;

// Created by Sim on 2019-09-17.
// 알람에 필요한 데이터를 객체화 한다

public class Alarm {
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    //Parameters, don't reference parameters directly, use setter and getter
    private int id = -1;
    /* HJ SJ START  */
    private boolean enable = true; // 1 : true, 0 : false
    /* HJ SJ END */
    private String alarmDate = ""; //YYYYMMDD
    private String alarmTime = ""; //hhmmss
    /* HJ SJ START  */
    //private String duty = "";  //간격 : 반복 : 알림 ex) "3 : 3 : 3,7"
    private int alarmCycle = 0; // 반복 주기 (분: 1~60)
    private int alarmMaxCount = 0; // 반복 횟수 (회 : 0~99)
    private String alarmNotice = ""; // 알림(0 ~ xx, xx, xx)
    /* HJ SJ END */
    private String dayCircle = ""; //0,1,0,1,0,1,0 일,월,화,수,목,금,토  0 : 비활성 1: 활성
    private String alarmNote = ""; // 알람내용
    private String fileName = ""; // 파일제목, 알람음악
    private int vibeLevel = 50; //range 0 ~ 100, 진동세기, 10 단계로 나눌 것
    private int alarmCount = 0; // 알람울린횟수




    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    public Alarm()
    {
        //TODO make init, default setting
        //id = 1;
        /* HJ SJ START  */
        enable = true;
        /* HJ SJ END */
        alarmDate = "19990101";
        alarmTime = "000000";
        /* HJ SJ START  */
        //duty = "3:3:3,7";
        alarmCycle = 1;
        alarmMaxCount = 1;
        alarmNotice = "0";
        /* HJ SJ END */
        dayCircle = "0000000";
        alarmNote = "ALARM CONTENTS";
        fileName = "MUSIC NAME";
        /* HJ SJ STRAT */
        vibeLevel = 50;
        /* HJ SJ END */
        alarmCount = 0;
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    //TODO make setter getter
    public int getId(){
        return this.id;
    }

    /* HJ SJ START */
    public boolean getEnable() {
        return enable;
    }
    /* HJ SJ END */

    public String getAlarmDate() {
        return alarmDate;
    }

    public String getAlarmTime() {
        return alarmTime;
    }
    /* HJ SJ START */
    /*public String getDuty() {
        return duty;
    }
    */
    public int getAlarmCycle() {
        return alarmCycle;
    }

    public int getAlarmMaxCount() {
        return alarmMaxCount;
    }

    public String getAlarmNotice() {
        return alarmNotice;
    }
    /* HJ SJ END */
    public String getDayCircle() {
        return dayCircle;
    }

    public String getAlarmNote() {
        return alarmNote;
    }

    public String getFileName() {
        return fileName;
    }

    public int getVibeLevel() {
        return vibeLevel;
    }

    public int getAlarmCount() {
        return alarmCount;
    }

    //--------------------------------------------------------------------------------------------//
    public void setId(int id){
        this.id = id;
    }

    /* HJ SJ START */
    public void setEnable(boolean enable) {
        this.enable = enable;
}
    /* HJ SJ END */

    public void setAlarmDate(String alarmDate) {
        this.alarmDate = alarmDate;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    /* HJ SJ START  */
    /*  public void setDuty(String duty) {
        this.duty = duty;
    }*/

    public void setAlarmCycle(int alarmCycle) {
        this.alarmCycle = alarmCycle;
    }

    public void setAlarmMaxCount(int alarmMaxCount) {
        this.alarmMaxCount = alarmMaxCount;
    }

    public void setAlarmNotice(String alarmNotice) {
        this.alarmNotice = alarmNotice;
    }
    /* HJ SJ END */

    public void setDayCircle(String dayCircle) {
        this.dayCircle = dayCircle;
    }

    public void setAlarmNote(String alarmNote) {
        this.alarmNote = alarmNote;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setVibeLevel(int vibeLevel) {
        this.vibeLevel = vibeLevel;
    }

    public void setAlarmCount(int alarmCount) {
        this.alarmCount = alarmCount;
    }
//--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//

}
