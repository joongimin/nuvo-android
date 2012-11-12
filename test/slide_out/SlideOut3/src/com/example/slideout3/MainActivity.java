package com.example.slideout3;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftMenu = (RelativeLayout) findViewById(R.id.leftMenu);
        leftMenu.setVisibility(View.INVISIBLE);
        rightMenu = (RelativeLayout) findViewById(R.id.rightMenu);
        rightMenu.setVisibility(View.INVISIBLE);

        main = (LinearLayout) findViewById(R.id.main);

		//// Get screen size info
	    WindowManager wm = ((Activity)this).getWindowManager();
	    Display display = wm.getDefaultDisplay();
	    DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        
        
        
		FrameLayout.LayoutParams param = (FrameLayout.LayoutParams) main.getLayoutParams();
		
		
		
		param.width = 580;
		param.leftMargin = -100;
		main.setLayoutParams(param);
		
		main.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("MA", "main Click");
				
			}
		});
		
        Button btnMain = (Button) findViewById(R.id.btnMain);
        
        btnMain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Log.i("MA", "btn Click");
				
				FrameLayout.LayoutParams param = (FrameLayout.LayoutParams) main.getLayoutParams();
//				param.leftMargin = 200;
				main.setLayoutParams(param);
				
				TranslateAnimation ta = new TranslateAnimation(0, 480.0f, 0, 0);
				ta.setDuration(2000);
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
