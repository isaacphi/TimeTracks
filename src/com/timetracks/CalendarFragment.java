package com.timetracks;


import java.util.Date;
import java.util.List;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.timetracks.backend.BackendInterface;
import com.timetracks.models.TimesheetEntry;

public class CalendarFragment extends Fragment {
	private static final int ID_VIEW_OTHER_FRAGMENT = 1;
	private static final int ID_TAG = 2;
	private static final int ID_EXCLUDE = 3;
	
    @SuppressWarnings("deprecation")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_calendar_fragment, container, false);
        
        class GetTimesheetEntries extends AsyncTask<Void, Void, List<TimesheetEntry>> {
        	CalendarFragment container;
        	public GetTimesheetEntries(CalendarFragment container) {
        		this.container=container;
        	}
        	@Override
        	protected List<TimesheetEntry> doInBackground(Void...voids) {
        		BackendInterface backend = BackendInjector.getBackend();
        		ViewWindow.calculateDates();
        		return backend.getTimesheetEntries(ViewWindow.startDate, ViewWindow.endDate);
        	}
        	@Override
        	protected void onPostExecute(List<TimesheetEntry> entriesList) {
        		this.container.renderTimesheetEntries(rootView, entriesList);
        	}   	

        }
        new GetTimesheetEntries(this).execute();       
        return rootView;
    }
	
    View renderTimesheetEntries(View rootView, List<TimesheetEntry> entriesList) {
    	RelativeLayout edit = null;
        int color = 0;
        
        int[] viewIds = {
        		R.id.sundayRelativeLayout,
        		R.id.mondayRelativeLayout,
        		R.id.tuesdayRelativeLayout,
        		R.id.wednesdayRelativeLayout,
        		R.id.thursdayRelativeLayout,
        		R.id.fridayRelativeLayout,
        		R.id.saturdayRelativeLayout,
        };
        
        for (int j=0;j<7;j++){
        	edit = (RelativeLayout) rootView.findViewById(viewIds[j]);
	        for (int i=0;i<entriesList.size();i++) {
	        	Date start = entriesList.get(i).startDate;
	        	Date end = entriesList.get(i).endDate;	        	
	        	
	        	if ( start.getDay()==j ) {
	        		if (entriesList.get(i).colourCode == "blue") {
	        			color = 0;
	        		} else if (entriesList.get(i).colourCode == "red") {
 	        		} else if (entriesList.get(i).colourCode == "yellow") {
	        			color = 2;
	        		} else if (entriesList.get(i).colourCode == "orange") {
	        			color = 3;
	        		} else if (entriesList.get(i).colourCode == "azure") {
	        			color = 4;
	        		} else { 
	        			color = 4;
	        		}
	        		RectView rv = insertEvent(start.getHours(), end.getHours()-start.getHours(), color);
	        		
	        		
	        		rv.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							QuickAction quickAction = setQuickActionButtons();
							quickAction.show(v);
						}
					});
	        		
	        		edit.addView(rv);
	        	}
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
	
public QuickAction setQuickActionButtons()	{
		
		Intent intent = getActivity().getIntent();
		
		// Should have variable text and logos...in onResume?
		ActionItem viewOtherFragment;

		viewOtherFragment = new ActionItem(ID_VIEW_OTHER_FRAGMENT, "Map", getResources().getDrawable(R.drawable.ic_action_map));

		ActionItem tagItem;
		// Need to detect things from DB before wiring this up. We'll use the same symbol though
		tagItem = new ActionItem(ID_TAG, "Tag", getResources().getDrawable(R.drawable.ic_action_new_label));
		ActionItem exclude = new ActionItem(ID_EXCLUDE, "Exclude", getResources().getDrawable(R.drawable.ic_action_cancel));
		
		final QuickAction quickAction = new QuickAction(getActivity(), QuickAction.HORIZONTAL);
		
		quickAction.addActionItem(viewOtherFragment);
		quickAction.addActionItem(tagItem);
		quickAction.addActionItem(exclude);
		
		quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			@Override
			public void onItemClick(QuickAction quickAction, int pos, int actionId) {
				ActionItem actionItem = quickAction.getActionItem(pos);
				
				if (actionId == ID_VIEW_OTHER_FRAGMENT) {
					// Start Activity of view Other Fragment
					
				} else if (actionId == ID_TAG) {
					// Select Activity of Tagging or Retagging
				} else {
					// Start Activity of Exclude
				}
			}
		});
		
		quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {			
			@Override
			public void onDismiss() {
				// Nothing to display, just close the window naturally
			}
		});
		return quickAction;
	} 
	
}
