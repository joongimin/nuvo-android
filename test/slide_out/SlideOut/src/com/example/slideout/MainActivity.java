package com.example.slideout;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
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
	
	Bitmap bmCover;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView v = (ImageView) findViewById(R.id.vCover);
        v.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
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
				Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();
				Drawable drawable = new BitmapDrawable(bmCover);
				vgMenuLeft.setBackgroundDrawable(drawable);
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
