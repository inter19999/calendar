package com.example.calendar22;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MonthCalendarAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS=100;
    //50번째 페이지를 현재 년 월로 정한다.
    Map<Integer,Integer> Yearmap=new HashMap<Integer,Integer>();
    Map<Integer,Integer> Monthmap=new HashMap<Integer,Integer>();
    public MonthCalendarAdapter(@NonNull Fragment fragment) {
        super(fragment);
        //월 계산
        Calendar cal=Calendar.getInstance();
        int year;
        int month;
        cal.setTime(new Date());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<NUM_ITEMS;i++){
            cal=Calendar.getInstance();
            cal.add(Calendar.MONTH,i-50);
            Yearmap.put(i,cal.get(Calendar.YEAR));
            Monthmap.put(i,cal.get(Calendar.MONTH));
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int year = Yearmap.get(position),month = Monthmap.get(position);
        return MonthCalendarFragment.newInstance(year,month);
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}
