package com.example.slideout3;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	 RelativeLayout leftMenu;
     RelativeLayout rightMenu;

     LinearLayout main;
     Button btnMain;
     int btnIdx = 0;
     
     DisplayMetrics metrics;
     int width;
     
     Handler handler;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnMain = (Button) findViewById(R.id.btnMain);
        handler = new Handler();
        handler.post(new Runnable() {
			@Override
			public void run() {
				btnMain.setText(String.valueOf(btnIdx++));
				handler.postDelayed(this, 100);
			}
		});

        leftMenu = (RelativeLayout) findViewById(R.id.leftMenu);
        leftMenu.setVisibility(View.INVISIBLE);
        rightMenu = (RelativeLayout) findViewById(R.id.rightMenu);
        rightMenu.setVisibility(View.INVISIBLE);

        main = (LinearLayout) findViewById(R.id.main);

		//// Get screen size info
	    WindowManager wm = ((Activity)this).getWindowManager();
	    Display display = wm.getDefaultDisplay();
	    metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        
        
		FrameLayout.LayoutParams param = (FrameLayout.LayoutParams) main.getLayoutParams();
		
		width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, metrics);
		
		param.width = metrics.widthPixels + width*2;
		param.leftMargin = -width;
		main.setLayoutParams(param);
		main.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Log.i("MA", "main Click");
				
				TranslateAnimation ta = new TranslateAnimation(0, -metrics.widthPixels+width, 0, 0);
				ta.setDuration(2000);
				ta.setFillEnabled(true);
				ta.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						for(int i=0; i<main.getChildCount(); i++) {
							main.getChildAt(i).setClickable(true);
						}
						leftMenu.setVisibility(View.INVISIBLE);
						
						FrameLayout.LayoutParams param = (FrameLayout.LayoutParams) main.getLayoutParams();
						param.leftMargin = -width;
						main.setLayoutParams(param);
						main.clearAnimation();
					}
				});
				main.startAnimation(ta);
			}
		});
		
        Button btnMain = (Button) findViewById(R.id.btnMain);
        
        btnMain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Log.i("MA", "btn Click");
				
				TranslateAnimation ta = new TranslateAnimation(0, metrics.widthPixels-width, 0, 0);
				ta.setDuration(2000);
				ta.setFillEnabled(true);
				ta.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						leftMenu.setVisibility(View.VISIBLE);
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						FrameLayout.LayoutParams param = (FrameLayout.LayoutParams) main.getLayoutParams();
						param.leftMargin = metrics.widthPixels-width*2;
						main.setLayoutParams(param);
						main.clearAnimation();
					}
				});
				main.startAnimation(ta);
				
				for(int i=0; i<main.getChildCount(); i++) {
					main.getChildAt(i).setClickable(false);
				}
//				main.setClickable(false);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
