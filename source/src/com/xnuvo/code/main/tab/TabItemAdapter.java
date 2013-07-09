package com.xnuvo.code.main.tab;

import java.util.ArrayList;

import com.xnuvo.R;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class TabItemAdapter extends PagerAdapter {

	private Context context;
	private int count;
	private int bufferRatio;
	
	private int[] mColorArray = {Color.YELLOW, Color.BLUE, Color.CYAN, Color.WHITE}; 
//			Color.DKGRAY, Color.GRAY, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.WHITE}; 
	
	private ArrayList<TextView> tvList;
	
	public TabItemAdapter(Context context, int count, int bufferRatio) {
		this.context = context;
		this.count = count;
		this.bufferRatio = bufferRatio;
		
		tvList = new ArrayList<TextView>();
		TextView tv = null;
		for(int i=0; i<count; i++) {
			tv = new TextView(context);
			tv.setBackgroundResource(R.drawable.bg_patter);
			tvList.add(tv);
		}
	}
	
	@Override
	public Object instantiateItem(View container, int position) {

//		TextView tv = new TextView(context);
		TextView tv = tvList.get(position % count);
		
		Log.i("VP", position+" "+tv.toString());
		tv.setText("ViewPager Item"+(position % count)+"\n"+tv.toString());				//글자지정
		
		position %= count;
		
//		tv.setBackgroundColor(mColorArray[position]);			//배경색 지정
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);		//글자 크기 24sp
		tv.setTextColor(mColorArray[mColorArray.length - (position+1)]);	//글자 색상은 배경과 다른 색으로
		
		try{
			((ViewPager)container).removeView(tv);
			((ViewPager)container).addView(tv, 0);		//뷰 페이저에 추가
		} catch(Exception e) {
			Log.i("ViewPager", e.getMessage());
		}
		
		return tv;
	}
	
	@Override
	public int getCount() {
		return count * bufferRatio;
	}

	public int getCircularFrontCount() {
		return count*(bufferRatio/2)+1;
	}
	
	public int getCircularBackCount() {
		return count*(bufferRatio/2)+count-1;
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view==obj;
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
	}

}
