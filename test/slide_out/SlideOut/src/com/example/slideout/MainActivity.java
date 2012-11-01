package com.example.slideout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	
	ViewFlipper vfMain;
	
	ViewGroup vgMenuLeft;
	ViewGroup vgMenuRight;
	
	Button btnMenuLeft;
	Button btnMenuRight;
	
	ImageView ivCover;
	Bitmap bmCover;
	ViewGroup vgCover;
	View vLeftShadow;
	View vRightShadow;

	private static final int DURATION_MS = 1000;
	private Animation mStartAnimation;
	private Animation mStopAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vLeftShadow = findViewById(R.id.vLeftShadow);
        vRightShadow = findViewById(R.id.vRightShadow);
        
        vLeftShadow.setVisibility(View.GONE);
        vRightShadow.setVisibility(View.GONE);
        
        vgCover = (ViewGroup) findViewById(R.id.vgCover);
        boolean mReverse = false;
        int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
		int displayWidth = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        int shift = (mReverse ? -1 : 1) * (width - displayWidth);
    	mStartAnimation = new TranslateAnimation(
				TranslateAnimation.ABSOLUTE, 0,
				TranslateAnimation.ABSOLUTE, -shift,
				TranslateAnimation.ABSOLUTE, 0,
				TranslateAnimation.ABSOLUTE, 0
				);
		mStartAnimation.setDuration(DURATION_MS);
		mStartAnimation.setFillAfter(true);
		mStartAnimation.setAnimationListener(new AnimationListener() {
			
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
				// TODO Auto-generated method stub
				
			}
		});

		mStopAnimation = new TranslateAnimation(
				TranslateAnimation.ABSOLUTE, -shift,
				TranslateAnimation.ABSOLUTE, 0,
				TranslateAnimation.ABSOLUTE, 0,
				TranslateAnimation.ABSOLUTE, 0
				);
		mStopAnimation.setDuration(DURATION_MS);
		mStopAnimation.setFillAfter(true);
		mStopAnimation.setAnimationListener(new AnimationListener() {
			
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
				ivCover.setBackgroundColor(Color.TRANSPARENT);
		        vLeftShadow.setVisibility(View.GONE);
		        vRightShadow.setVisibility(View.GONE);
				vfMain.setDisplayedChild(0);
			}
		});
        
        ivCover = (ImageView) findViewById(R.id.vCover);
        ivCover.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if(vfMain.getDisplayedChild() != 0) {
					vgCover.setAnimation(mStopAnimation);
			        return true;
				}
				return false;
			}
		});

        vfMain = (ViewFlipper) findViewById(R.id.vfMain);
        vfMain.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				return false;
			}
		});
        
        vgMenuLeft = (ViewGroup) findViewById(R.id.vgMenuLeft);
        vgMenuLeft.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				return false;
			}
		});
        vgMenuRight = (ViewGroup) findViewById(R.id.vgMenuRight);

        btnMenuLeft = (Button) findViewById(R.id.menu_left);
        btnMenuLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				updateCover();
				Drawable drawable = new BitmapDrawable(bmCover);
				ivCover.setBackgroundDrawable(drawable);
		        vLeftShadow.setVisibility(View.VISIBLE);
		        
		        vgCover.setAnimation(mStartAnimation);
				Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();
				vfMain.setDisplayedChild(1);
			}
		});
        btnMenuRight = (Button) findViewById(R.id.menu_right);
        btnMenuRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				updateCover();
				Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
				vfMain.setDisplayedChild(2);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void updateCover() {
		
		if (bmCover != null) {
			bmCover.recycle();
		}
		
		Rect rectgle = new Rect();
		Window window = getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
		int statusBarHeight = rectgle.top;

		ViewGroup v1 = (ViewGroup) findViewById(R.id.vgMain).getRootView();
		v1.setDrawingCacheEnabled(true);
		Bitmap source = Bitmap.createBitmap(v1.getDrawingCache());
		v1.setDrawingCacheEnabled(false);
		if (statusBarHeight != 0) {
			bmCover = Bitmap.createBitmap(source, 0, statusBarHeight, source.getWidth(), source.getHeight() - statusBarHeight);
//			Canvas c = new Canvas(bmCover);
//			Paint p = new Paint();
//			p.setColor(Color.RED);
//			c.drawRect(new Rect(10, 10, 50, 50), p);
			source.recycle();
		} else {
			bmCover = source;
		}
    }
}
