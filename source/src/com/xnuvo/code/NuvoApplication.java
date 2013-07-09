package com.xnuvo.code;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class NuvoApplication extends Application {

	public static DisplayMetrics display;
	public static int widthPixels;
	public static int heightPixels;

	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	public void setInfoByFirstActivity(Context context) {
        
		// Get screen size info
	    WindowManager wm = ((Activity)context).getWindowManager();
	    display = new DisplayMetrics();
	    wm.getDefaultDisplay().getMetrics(display);
	    
	    widthPixels = display.widthPixels;
	    heightPixels = display.heightPixels;
	    
	}
}
