package com.andstudy.alarmmanager.model;

// Created by Sim on 2019-09-17.
//알람데이터를 관리하는 클래스를 작성한다

import com.andstudy.alarmmanager.util.SqlLiteUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AlarmManager {
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    //싱글톤 객체
    private static AlarmManager instance;

    // Parameters
    private ArrayList<Alarm> listAlarm = null;

    //Test variable
    private String testInsertString = "in:";
    private String testUpdateString = "";
    private String testDeleteString = "";


    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    //싱글톤 객체를 얻기위한 함수
    public static AlarmManager getInstance() {
        if( instance == null )
            instance = new AlarmManager();
        return instance;
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    //생성자
    public AlarmManager(){
        //TODO make init
        listAlarm = new ArrayList<>();
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    public ArrayList<Alarm> GetAlarmList()
    {
        //TODO make function
        return listAlarm;
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    public Alarm GetAlarm(int alarmId)
    {
        //TODO make function
        return listAlarm.get(alarmId);
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    //TODO make method
    //Good luck !
    public boolean AddAlarm(Alarm alarm){
        SqlLiteUtil.getInstance().insert(alarm);
        return true;
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    public boolean SetAlram(Alarm alarm) { // 알람을 셋팅한다는데 뭐지?
        if(alarm == null) return false;
        return true;
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    public boolean SetAlramEnable(boolean onOff, int alarmId) {
        // update(onOff, alarmId); //update기능 구현 필요.
        return true;
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    public boolean DeleteAlarm(List<Integer> id) {
        for (int i = 0 ; i < id.size() ; i++)
        {
            // delete(id.get(i)); // delete기능 구현 필요.
        }
        return true;
    }

    public boolean DeleteAlarm(int id) {
        // delete(id); // delete기능 구현 필요.
        return true;
    }

    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    public boolean DeleteAlarm(String str) {
//		SETTING_DELETE 삭제 규식 타입 : 알람내용 , de : "문자열"
        return true;
    }
    //--------------------------------------------------------------------------------------------//
    //
    //--------------------------------------------------------------------------------------------//
    public boolean AddAlarm(String str) {
//	SETTING_ADD 추가 정규식 타입 : 알람내용 : 시간 : 요일,
	    /*
	     * private int id = 알아서;
	    private boolean enable = true; // 알람 울리기
	    private String alarmDate = ""; //YYYYMMDD = "00000000"
	    private String alarmTime = ""; //hhmmss = "SET"
	    private int alarmCycle = 0; // 반복 주기 (분: 1~60)
	    private int alarmMaxCount = 0; // 반복 횟수 (회 : 0~99)
	    private String alarmNotice = ""; // 알림(0 ~ xx, xx, xx)
	    private String dayCircle = ""; //0,1,0,1,0,1,0 일,월,화,수,목,금,토  0 : 비활성 1: 활성
	    private String alarmNote = ""; // 알람내용
	    private String fileName = ""; // 파일제목, 알람음악
	    private int vibeLevel = 50; //range 0 ~ 100, 진동세기, 10 단계로 나눌 것
	    private int alarmCount = 0; // 알람울린횟수
	     */

        return true;
    }
    //텍스트 정규화 메소드
    public void RegularizeText(String str) {
        // 1. str에서 첫번째 단어를 가져옵니다 - delete/insert구분
        // 2. 뒤에 내용 다 보내기!
        // 1) DeleteAlarm(str);
        // 2) AddAlarm(str);
        // 3) UpdateAlarm(str); - 기획 내용엔 有. 회의 내용엔 無
        //str - in:알람내용:002000:0001000
        //StringTokenizer txt = new StringTokenizer("2012/09/23", "/");
        StringTokenizer txt = new StringTokenizer("in:알람내용:002000:0001000", ":");
        String [] array = new String[txt.countTokens()];
        String tmp;
        int i = 0;
        Alarm alarm = new Alarm();

        while (txt.hasMoreTokens()) {
            //아직 파싱할 토큰이 더 있는지 여부를 확인한다
            //System.out.println("hasTokens " + txt.nextToken());
            //array[i++] = txt.nextToken();
            array[i++] = txt.nextToken();
        }

        if(array[0].equals("in")) {
            if( alarm.checkFormatAlarmNote(array[1]) &&
                    alarm.checkFormatAlarmTime(array[2]) &&
                    alarm.checkFormatDayCircle(array[3])) {
                alarm = new Alarm(array[1], array[2], array[3]);
                // insert
                System.out.println("insert 실행");
                //SqlLiteUtil.getInstance().insert(alarm);
            }
        } else if (array[0].equals("de")) {
            // delete
            System.out.println("insert 실행");
        }

    }
}
