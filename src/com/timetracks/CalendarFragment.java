package com.timetracks;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
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

    @SuppressWarnings("deprecation")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_calendar_fragment, container, false);
        RelativeLayout edit = (RelativeLayout) rootView.findViewById(R.id.tuesdayRelativeLayout);// mContainerIconExtension in your case
        
		List<TimesheetEntry> fakeData = new ArrayList<TimesheetEntry>();
		fakeData = TimesheetEntryValues.getEntries();
		
		String CURRENT_MONTH = "Nov ";
        String CURRENT_YEAR = "2013";
        int START_DATE = 24;

        for (int i=0;i<fakeData.size();i++) {
        	Date start = fakeData.get(i).startDate;
        	Date end = fakeData.get(i).endDate;
        	Date today = Helpers.dateHelper(CURRENT_YEAR+"-11-"+String.valueOf(START_DATE+i)+"-0-0-0");
        	Date tomorrow = Helpers.dateHelper(CURRENT_YEAR+"-11-"+String.valueOf(START_DATE+1+i)+"-0-0-0");
        	if ( (start.getHours()>today.getHours()) && (end.getHours()<tomorrow.getHours()) ) {
        		RectView rv = insertEvent(start.getHours(), start.getHours()-end.getHours(), 1);
        		edit.addView(rv);
        	}
        }
        
        
        return rootView;
    }
	
	private class RectView extends View{
		int [] colors = {getResources().getColor(R.color.blue), getResources().getColor(R.color.red),
				getResources().getColor(R.color.yellow), getResources().getColor(R.color.green),
				getResources().getColor(R.color.Orange), getResources().getColor(R.color.Azure)};
	    int leftX, rightX, topY, bottomY, yOffset, color;
	    private Paint rectPaint;
	    private Rect rectangle;
	    public RectView(Context context, int _leftX, int _rightX, int _topY,
	    				int _bottomY, int _yOffset, int _color){
	        super(context);
	        leftX = _leftX;
	        rightX = _rightX;
	        topY = _topY;
	        bottomY = _bottomY;
	        yOffset = _yOffset;
	        color = _color;
	        init();
	    }
	    private void init(){
	    	rectPaint = new Paint();
	        rectPaint.setARGB(255, 0, 0, 0);
	        rectPaint.setColor(colors[color]);
	        rectPaint.setStrokeWidth(2);
	        rectangle = new Rect(leftX, topY+yOffset, rightX, bottomY+yOffset);
	    }
	    protected void onDraw(Canvas canvas){
	        super.onDraw(canvas);
	        canvas.drawRect(rectangle, rectPaint);
	    }
	}
	
	RectView insertEvent(float time, float length, int color) {
        RectView rv = new RectView(getActivity(), 0, 100, 0,(int) length*120,(int) time*120, color);
        return rv;
	}
	
}
