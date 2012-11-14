package com.xnuvo.code.main;

import com.xnuvo.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {

	// Screen info
	private DisplayMetrics display;
	private final static int shadowDp = 10;
	private int shadowWidth; 

	// Menu info
    private final int animTime = 750;
    private final static int MENU_CATEGORY = 0;
    private final static int MENU_NOTIFICATION = 1;
    private int menuMode = 0;
    private final static int menuBtnDp = 52;
    private int menuBtnWidth;
	
	// Layout component
    private FrameLayout vgTotal;
    
	private Button btnCateMenu;
	private Button btnNotiMenu;

	private LinearLayout viewMain;
	private RelativeLayout vgMain;
	
	private LinearLayout menuLeft;
	private LinearLayout menuRight;
	private ImageView ivShadowLeft;
	private ImageView ivShadowRight;
	
	private ViewFlipper vfTabMain;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		// Get screen size info
	    WindowManager wm = ((Activity)this).getWindowManager();
	    display = new DisplayMetrics();
	    wm.getDefaultDisplay().getMetrics(display);
	    
	    // dp size to width size
		shadowWidth = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, shadowDp, display);
		menuBtnWidth = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, menuBtnDp, display);
		
        // Layout initialize
        setContentView(R.layout.activity_main);
        
        // Layout variable connect  
        //// Title Button
        btnCateMenu = (Button) findViewById(R.id.btnCateMenu);
        btnCateMenu.setOnClickListener(OnClickListener);
        btnNotiMenu = (Button) findViewById(R.id.btnNotiMenu);
        btnNotiMenu.setOnClickListener(OnClickListener);
        //// View Main
        viewMain = (LinearLayout) findViewById(R.id.viewMain);
        FrameLayout.LayoutParams viewMainParams = (FrameLayout.LayoutParams) viewMain.getLayoutParams();//new LinearLayout.LayoutParams(display.widthPixels, LinearLayout.LayoutParams.FILL_PARENT);
        viewMainParams.width = display.widthPixels + shadowWidth*2;
        viewMainParams.leftMargin = -shadowWidth; 
        viewMain.setLayoutParams(viewMainParams);
        viewMain.setOnClickListener(OnClickListener);
        viewMain.setClickable(false);
        //// View Group Main (inside View Main)
        vgMain = (RelativeLayout) findViewById(R.id.vgMain);
        LayoutParams vgMainParams = vgMain.getLayoutParams();
        vgMainParams.width = display.widthPixels;
        vgMain.setLayoutParams(vgMainParams);
        //// Menu Shadow
        LinearLayout.LayoutParams shadowParams = new LinearLayout.LayoutParams(shadowWidth, LinearLayout.LayoutParams.FILL_PARENT);
        ivShadowLeft = (ImageView) findViewById(R.id.ivShadowLeft); 
        ivShadowLeft.setLayoutParams(shadowParams);
        ivShadowRight = (ImageView) findViewById(R.id.ivShadowRight);
        ivShadowRight.setLayoutParams(shadowParams);
        //// Menu Layout
        menuLeft = (LinearLayout) findViewById(R.id.menuLeft);
        menuLeft.setVisibility(View.INVISIBLE);
        menuRight = (LinearLayout) findViewById(R.id.menuRight);
        menuRight.setVisibility(View.INVISIBLE);
        //// Main View
        vfTabMain = (ViewFlipper) findViewById(R.id.vfTabMain); 
        vfTabMain.setOnClickListener(OnClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    View.OnClickListener OnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.btnCateMenu:
				visibleMenu(MENU_CATEGORY);
				break;
			case R.id.btnNotiMenu:
				visibleMenu(MENU_NOTIFICATION);
				break;
			case R.id.viewMain:
				invisibleMenu();
				break;
			}
		}
	};

    public void visibleMenu(int mode) {
    	menuMode = mode;
    	
    	TranslateAnimation ta = null;
    	switch(menuMode) {
    	case MENU_CATEGORY:
    		ta = new TranslateAnimation(0, display.widthPixels-menuBtnWidth, 0, 0);
    		break;
    	case MENU_NOTIFICATION:
    		ta = new TranslateAnimation(0, -display.widthPixels+menuBtnWidth, 0, 0);
    		break;
    	}
		ta.setDuration(animTime);
		ta.setFillEnabled(true);
		ta.setAnimationListener(MenuOpenAnimationListener);
		viewMain.startAnimation(ta);
		
		childsClickable(viewMain, false);
    }
    
    public void childsClickable(ViewGroup parent, boolean clickable) {
    	View v = null;
    	for(int i=0; i<parent.getChildCount(); i++) {
    		v = parent.getChildAt(i);
    		v.setClickable(clickable);
    		if(v instanceof ViewGroup) {
    			Log.i("ML", "View Group: "+v.getClass().toString());
    			childsClickable((ViewGroup)v, clickable);
    		} else if(v instanceof View) {
    			Log.i("ML", "View: "+v.getClass().toString());
    		}
		}
    }

    Animation.AnimationListener MenuOpenAnimationListener = new AnimationListener() {
    	
		@Override
		public void onAnimationStart(Animation animation) {
			switch(menuMode) {
			case MENU_CATEGORY:
				menuLeft.setVisibility(View.VISIBLE);
				break;
			case MENU_NOTIFICATION:
				menuRight.setVisibility(View.VISIBLE);
				break;
			}
		}
		
		@Override
		public void onAnimationRepeat(Animation animation) {}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			
			FrameLayout.LayoutParams param = (FrameLayout.LayoutParams) viewMain.getLayoutParams();
			switch(menuMode) {
			case MENU_CATEGORY:
				param.leftMargin = display.widthPixels-shadowWidth-menuBtnWidth;
				break;
			case MENU_NOTIFICATION:
				param.leftMargin = -display.widthPixels-shadowWidth+menuBtnWidth;
				break;
			}
			viewMain.setLayoutParams(param);
			viewMain.clearAnimation();
			viewMain.setClickable(true);
		}
	};

    public void invisibleMenu() {
    	
    	TranslateAnimation ta = null;
    	switch(menuMode) {
    	case MENU_CATEGORY:
    		ta = new TranslateAnimation(0, -(display.widthPixels-menuBtnWidth), 0, 0);
    		break;
    	case MENU_NOTIFICATION:
    		ta = new TranslateAnimation(0, -(-display.widthPixels+menuBtnWidth), 0, 0);
    		break;
    	}
		ta.setDuration(animTime);
		ta.setFillEnabled(true);
		ta.setAnimationListener(MenuCloseAnimationListener);
		viewMain.startAnimation(ta);
    }

    Animation.AnimationListener MenuCloseAnimationListener = new AnimationListener() {
    	
		@Override
		public void onAnimationStart(Animation animation) {
			viewMain.setClickable(false);
		}
		
		@Override
		public void onAnimationRepeat(Animation animation) {}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			switch(menuMode) {
			case MENU_CATEGORY:
				menuLeft.setVisibility(View.INVISIBLE);
				break;
			case MENU_NOTIFICATION:
				menuRight.setVisibility(View.INVISIBLE);
				break;
			}
			
			FrameLayout.LayoutParams param = (FrameLayout.LayoutParams) viewMain.getLayoutParams();
			param.leftMargin = -shadowWidth;
			
			viewMain.setLayoutParams(param);
			viewMain.clearAnimation();
			
			childsClickable(viewMain, true);
		}
	};
}
