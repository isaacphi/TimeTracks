package com.timetracks;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Notification.Style;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.timetracks.bootstrapping.TimesheetEntryValues;
import com.timetracks.models.TimesheetEntry;

public class CalendarFragment extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		//Original:
        //View rootView = inflater.inflate(R.layout.activity_calendar_fragment, container, false);
        
		List<TimesheetEntry> fakeData = new ArrayList<TimesheetEntry>();
		fakeData = TimesheetEntryValues.getEntries();
		
		String CURRENT_MONTH = "Nov ";
        String CURRENT_YEAR = "2013";
        int START_DATE = 10;
        
        // Overall vertical layout
        LinearLayout my_root = new LinearLayout(getActivity());
        my_root.setOrientation(LinearLayout.VERTICAL);
        
        // Layout Parameters 
        LinearLayout.LayoutParams horizParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        horizParams.weight = 1.0f;
        horizParams.width = 50;
        horizParams.gravity=Gravity.RIGHT;
        horizParams.width=100;
        
        RelativeLayout.LayoutParams vertParams = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        vertParams.height=100;
                 
        // Horizontal layout which says month and year
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
        
        // Horizontal layout which lists the days
        LinearLayout days = new LinearLayout(getActivity());
        days.setOrientation(LinearLayout.HORIZONTAL);
               
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
        
        // Scroll View
        ScrollView scroll = new ScrollView(getActivity());
        // One for each day (first column is list of times)
        LinearLayout horizontalLayout = new LinearLayout(getActivity());
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        
        // List of times
        LinearLayout timeLayout = new LinearLayout(getActivity());
        timeLayout.setOrientation(LinearLayout.VERTICAL);

        //List<TextView> textArray = new ArrayList<TextView>();
        for (i=0;i<24;i++) {
        	TextView tv = new TextView(getActivity());
        	tv.setTextColor(getResources().getColor(R.color.cleargray));
        	tv.setLayoutParams(vertParams);
        	if (i<12) {
        		tv.setText(String.valueOf(i+1)+" am");
        	} else {
        		tv.setText(String.valueOf(i-11)+" pm");
        	}   	
        	timeLayout.addView(tv);
        }
        
        horizontalLayout.addView(timeLayout);
        
        // Each day of the week (vertical layouts)
        //List<LinearLayout> columns = new ArrayList<LinearLayout>();
        for (i=0;i<7;i++) {
        	LinearLayout ll = new LinearLayout(getActivity());
        	ll.setOrientation(LinearLayout.VERTICAL);
        	
        	//TextView tv = new TextView(getActivity());
        	//tv.setText("test");
        	//ll.addView(tv);
        	
        	RectView rv = new RectView(getActivity(), 50,100,50,100);
        	rv.setLayoutParams(vertParams);
        	
        	ll.addView(rv);
        	
        	horizontalLayout.addView(ll);
        	//horizontalLayout.addView(rv);
        }
        
        
        scroll.addView(horizontalLayout);
        my_root.addView(scroll);
        
        // RECTANGLE CONSTRUCTION
        RectView rv = new RectView(getActivity(), 0, 100, 0, 100);
        rv.setLayoutParams(vertParams);
        //timeLayout.addView(rv);
        
        return my_root;
    }
	
	private class RectView extends View{
	    int leftX, rightX, topY, bottomY;
	    private Paint rectPaint;
	    private Rect rectangle;
	    public RectView(Context context, int _leftX, int _rightX, int _topY, int _bottomY){
	        super(context);
	        leftX = _leftX;
	        rightX = _rightX;
	        topY = _topY;
	        bottomY = _bottomY;
	        init();
	    }
	    private void init(){
	    	rectPaint = new Paint();
	        rectPaint.setARGB(255, 0, 0, 0);
	        rectPaint.setColor(getResources().getColor(R.color.Gray));
	        rectPaint.setStrokeWidth(2);
	        rectangle = new Rect(leftX, topY, rightX, bottomY);
	    }
	    protected void onDraw(Canvas canvas){
	        super.onDraw(canvas);
	        canvas.drawRect(rectangle, rectPaint);
	    }
	}
	
	void insertEvent(List<TimesheetEntry> data) {

	}
	
}
