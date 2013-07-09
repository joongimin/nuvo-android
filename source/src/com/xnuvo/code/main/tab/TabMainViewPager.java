package com.xnuvo.code.main.tab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TabMainViewPager extends ViewPager {
	
	public TabMainViewPager(Context context) {
		super(context);
	}

	public TabMainViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	boolean isClickEnabled = true;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    if (this.isClickEnabled) {
	        return super.onTouchEvent(event);
	    }
	    return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
	    if (this.isClickEnabled) {
	        return super.onInterceptTouchEvent(event);
	    }
	    return false;
	}
	
	@Override
	public void setClickable(boolean clickable) {
		this.isClickEnabled = clickable;
		super.setClickable(clickable);
	}

}
