package com.example.movechangetest;

import android.R.color;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	
	Handler handler = new Handler();
	ViewGroup vgLayout;
	
	
	
	
	private void movingLeft() {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.move_left);
//        TranslateAnimation a = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        
        animation.setFillAfter(true);
        
        animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
//				vgLayout.getChildAt(0).setVisibility(View.GONE);
//				handler.post(new Runnable() {
//					@Override
//					public void run() {
//						vgLayout.removeViewAt(0);	
//					}
//				});
				vgLayout.removeViewAt(0);
				TextView ttv =  new TextView(MainActivity.this);
				ttv.setText("AddedView");
				ttv.setLayoutParams(containerParams);
				ttv.setBackgroundColor(Color.GREEN);
				vgLayout.addView(ttv);
		        animation.setFillAfter(false);
				vgLayout.clearAnimation();
//				startAnim();
			}
		});
//    	vgLayout.startAnimation(animation);
        for(int i=0; i<vgLayout.getChildCount(); i++) {
        	vgLayout.getChildAt(i).startAnimation(animation);
        }
	}
	
	private void movingRight() {
		
	}

    private StringBuffer buf = null;
    private WindowManager wm = null;
    private Display display = null;
    private DisplayMetrics metrics = null;
    LinearLayout.LayoutParams containerParams;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
//        vgLayout = (ViewGroup) findViewById(R.id.vgLayout);
//    	TextView tv;
//    	
//		//// Get screen size info
//	    WindowManager wm = ((Activity)this).getWindowManager();
//	    Display display = wm.getDefaultDisplay();
//	    DisplayMetrics metrics = new DisplayMetrics();
//        display.getMetrics(metrics);
//        
//    	
//    	int width = metrics.widthPixels / 3;//(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics()) / 3;
//		
//		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(metrics.widthPixels/3*5, ViewGroup.LayoutParams.WRAP_CONTENT);
//		containerParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
//		params.leftMargin = -width;
//		
//    	vgLayout.setLayoutParams(params);
//        for(int i=0; i<vgLayout.getChildCount(); i++) {
//        	tv = (TextView) vgLayout.getChildAt(i);
//        	tv.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					TextView tv = (TextView) v;
//					if(vgLayout.getChildAt(3) == v) {
//						movingLeft();
//					}
//					Toast.makeText(MainActivity.this, tv.getText(), Toast.LENGTH_SHORT).show();
//				}
//			});
//        	
//			
//        	tv.setLayoutParams(containerParams);//new LayoutParams(width, LayoutParams.WRAP_CONTENT));
////        	tv.startAnimation(animation);
//        }
        
        ViewGroup vgLayout2 = (ViewGroup) findViewById(R.id.vgLayout2);
        TabBarView ttl = new TabBarView(this);
        ttl.setInfo(new String[]{"뉴스 피드", "작품", "작가"}, 0);
        vgLayout2.addView(ttl);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}