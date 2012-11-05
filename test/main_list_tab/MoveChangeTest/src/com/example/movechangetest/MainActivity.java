package com.example.movechangetest;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
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
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	
	Handler handler = new Handler();

	private void startAnim() {
        StringBuffer buf = null;
        WindowManager wm = null;
        Display display = null;
        DisplayMetrics metrics = null;
        
        wm = getWindowManager();
        display = wm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.move_right);
        animation.setFillAfter(true);
        final ViewGroup vgLayout = (ViewGroup) findViewById(R.id.vgLayout);
        
        animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
//				vgLayout.getChildAt(0).setVisibility(View.GONE);
//				handler.post(new Runnable() {
//					@Override
//					public void run() {
//						vgLayout.removeViewAt(0);	
//					}
//				});
				vgLayout.removeViewAt(3);
		        animation.setFillAfter(false);
				vgLayout.clearAnimation();
//				startAnim();
			}
		});
    	vgLayout.startAnimation(animation);
    	TextView tv;
    	int width = metrics.widthPixels / 3;//(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics()) / 3;
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(metrics.widthPixels/3*4, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.leftMargin = -width;
		
    	vgLayout.setLayoutParams(params);
        for(int i=0; i<vgLayout.getChildCount(); i++) {
        	tv = (TextView) vgLayout.getChildAt(i);
        	tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					TextView tv = (TextView) v;
					if(v.getId() == R.id.tv4) {
						startAnim();
					}
					Toast.makeText(MainActivity.this, tv.getText(), Toast.LENGTH_SHORT).show();
				}
			});
        	
			LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
			
        	tv.setLayoutParams(containerParams);//new LayoutParams(width, LayoutParams.WRAP_CONTENT));
        	tv.startAnimation(animation);
        }
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startAnim();
        
//      
        
//        final TextView tv = (TextView) findViewById(R.id.tvTest);
//        NoRestoreAnimation animation = new NoRestoreAnimation();
        
        
        
//        final TextView tv1 = (TextView) findViewById(R.id.tv1);
//        animation.setAnimationListener(new AnimationListener() {
//			@Override
//			public void onAnimationStart(Animation animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationEnd(Animation animation) {
//		        tv1.setVisibility(View.GONE);
//				
//			}
//		});
//        tv.startAnimation(animation);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}