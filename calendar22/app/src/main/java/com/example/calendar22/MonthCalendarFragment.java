package com.example.calendar22;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthCalendarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    public MonthCalendarFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MonthCalendarFragment newInstance(int year, int month) {
        MonthCalendarFragment fragment = new MonthCalendarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.monthcalendar_fragment, container, false);

        int j=0;
        int year=0;
        int month=0;
        int dayofweek=1;
        int lastday;
        mParam1=getArguments().getInt(ARG_PARAM1);
        mParam2=getArguments().getInt(ARG_PARAM2);
        year=mParam1;
        month=mParam2;

        int finalmonth=month+1;
        getActivity().setTitle(year+"년 "+(finalmonth)+"월");

        dayofweek=getDayofWeek(year,month);
        switch (month+1) {
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                    lastday = 29;
                else lastday = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                lastday = 30;
                break;
            default:
                lastday = 31;
                break;
        }

        String[] items = new String[42];
        for(int i=0;i<42;i++){
            items[i]=" ";
        }
        for(int i=1;i<dayofweek;i++){
            items[j]=" ";
            j++;
        }
        for (int i = 0; i < lastday; i++) {
            items[j] = Integer.toString(i + 1);
            j++;
        }
        //그리드셀 조정 출처:https://whatisthenext.tistory.com/21


        GridView gridview=(GridView) view.findViewById(R.id.gridview);
        ArrayAdapter<String> adapt
                = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, items){
            @Override
            public View getView(int position,View convertView,ViewGroup parent){
                TextView tv_cell=(TextView) super.getView(position,convertView,parent);
                int gridviewH=gridview.getHeight();
                tv_cell.setGravity(Gravity.CENTER);
                tv_cell.setHeight(gridviewH/6);
                tv_cell.setBackgroundColor(Color.WHITE);
                return tv_cell;
            }
        };


        // 어댑터를 GridView 객체에 연결 출처 https://coding-restaurant.tistory.com/94
        gridview.setAdapter(adapt);
        int finalYear = year;
        int finalMonth = month;
        int finalDayofweek = dayofweek;
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if((int)id- finalDayofweek +2>0){
                    Toast.makeText(getContext(), finalYear +"."+(finalMonth +1)+"."+((int)id- finalDayofweek +2),Toast.LENGTH_SHORT).show();
                }
                view.setBackgroundColor(Color.CYAN);
            }
        });



        return view;
    }

    // 연 월 계산 출처 https://m.blog.naver.com/PostView.nhn?blogId=heartflow89&logNo=220966558486&proxyReferer=https:%2F%2Fwww.google.com%2F
    public int getDayofWeek(int year,int month){
        int []end_day= new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
            end_day[1]=29;
        year--;
        int dayofweek=year*365+year/4-year/100+year/400;
        for(int i=0;i<month;i++){
            dayofweek+=end_day[i];
        }
        dayofweek++;
        return dayofweek%7+1;
    }
}