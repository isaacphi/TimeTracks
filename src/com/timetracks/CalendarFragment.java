package com.timetracks;

import java.util.ArrayList;
import java.util.List;

import com.timetracks.bootstrapping.TimeSheetEntryValues;
import com.timetracks.models.TimesheetEntry;

import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CalendarFragment extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		//Original:
        //View rootView = inflater.inflate(R.layout.activity_calendar_fragment, container, false);
        
		List<TimesheetEntry> fakeData = new ArrayList<TimesheetEntry>();
		fakeData = TimeSheetEntryValues.getEntries();
		System.out.println(fakeData.get(1).note);
		
		String CURRENT_MONTH = "Dec ";
        String CURRENT_YEAR = "2013";
        int START_DATE = 1;
        
        LinearLayout my_root = new LinearLayout(getActivity());
        my_root.setOrientation(LinearLayout.VERTICAL);
        
        LinearLayout monthYear = new LinearLayout(getActivity());
        monthYear.setOrientation(LinearLayout.HORIZONTAL);
        
        TextView t1 = new TextView(getActivity());
        t1.setText(CURRENT_MONTH);
        t1.setTextSize(30);
        t1.setTextColor(getResources().getColor(R.color.cleargray));
        monthYear.addView(t1);
        TextView t2 = new TextView(getActivity());
        t2.setText(CURRENT_YEAR);
        t2.setTextSize(30);
        t2.setTextColor(getResources().getColor(R.color.Gray));
        monthYear.addView(t2);
        my_root.addView(monthYear);
        
        LinearLayout days = new LinearLayout(getActivity());
        days.setOrientation(LinearLayout.HORIZONTAL);
        
        LinearLayout.LayoutParams horizParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        horizParams.weight = 1.0f;
        horizParams.width = 50;
        horizParams.gravity=Gravity.RIGHT;
        //horizParams.height=30;
        horizParams.width=100;
        
        LinearLayout.LayoutParams vertParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //vertParams.weight = 4.0f;
        vertParams.gravity=Gravity.LEFT;
        vertParams.height=60;
                
        TextView t3 = new TextView(getActivity());
        t3.setText("");
        t3.setLayoutParams(horizParams);
        days.addView(t3);
        //List<TextView> daysArray = new ArrayList<TextView>();
        int i = 0;
        for (String st : new String[]{"Sun","Mon","Tue","Wed","Thu","Fri","Sat"}) {
        	TextView tv = new TextView(getActivity());
        	tv.setLayoutParams(horizParams);
        	tv.setTextColor(getResources().getColor(R.color.Gray));
        	tv.setTextSize(14);
        	tv.setText(st+" "+String.valueOf(START_DATE+i));
        	i++;
        	days.addView(tv);
        }
        my_root.addView(days);
        
        ImageView divider = new ImageView(getActivity());
        LinearLayout.LayoutParams lp = 
            new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 2);
        //lp.setMargins(left, top, right, bottom);
        lp.height=2;
        divider.setLayoutParams(lp);
        divider.setBackgroundColor(getResources().getColor(R.color.Gray));
        
        
        ScrollView scroll = new ScrollView(getActivity());

        LinearLayout timeLayout = new LinearLayout(getActivity());
        timeLayout.setOrientation(LinearLayout.VERTICAL);
        
        //List<TextView> textArray = new ArrayList<TextView>();
        for (i=0;i<24;i++) {
        	TextView tv = new TextView(getActivity());
        	tv.setLayoutParams(vertParams);
        	tv.setTextColor(getResources().getColor(R.color.cleargray));
        	if (i<12) {
        		tv.setText(String.valueOf(i+1)+" am");
        	} else {
        		tv.setText(String.valueOf(i-11)+" pm");
        	}
        	timeLayout.addView(tv);
        	//textArray.add(tv);
        }
        
        scroll.addView(timeLayout);
        my_root.addView(scroll);

        return my_root;
    }
	
}
