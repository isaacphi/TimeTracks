package com.timetracks;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.timetracks.backend.BackendInterface;
import com.timetracks.models.TimesheetEntry;

public class CalendarFragment extends Fragment {
	public BackendInterface backend;
	
    @SuppressWarnings("deprecation")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	backend = BackendInjector.getBackend();
        final View rootView = inflater.inflate(R.layout.activity_calendar_fragment, container, false);
        final RelativeLayout edit = (RelativeLayout) rootView.findViewById(R.id.tuesdayRelativeLayout);// mContainerIconExtension in your case
        
        ViewWindow.calculateDates();
        class GetTimesheetEntries extends AsyncTask<Date, Void, List<TimesheetEntry>> {
        	CalendarFragment container;
        	public GetTimesheetEntries(CalendarFragment container) {
        		this.container=container;
        	}
        	@Override
        	protected List<TimesheetEntry> doInBackground(Date... dates) {
        		return backend.getTimesheetEntries(dates[0], dates[1]);
        	}
        	@Override
        	protected void onPostExecute(List<TimesheetEntry> entriesList) {
        		this.container.renderTimesheetEntries(rootView, entriesList);
        	}   	
        }
        new GetTimesheetEntries(this).execute(ViewWindow.startDate, ViewWindow.endDate);
       
        return rootView;
    }
    
    void renderTimesheetEntries(View rootView, List<TimesheetEntry> timesheetEntryList) {
    	
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
