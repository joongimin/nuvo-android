package com.xnuvo.code.main;

import com.xnuvo.R;
import com.xnuvo.engine.lib.slideout.SlideoutActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	private Button btnTabMenu;
	private Button btnNotiMenu;  
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Layout initialize
        setContentView(R.layout.activity_main);
        
        // Layout variable connect  
        btnTabMenu = (Button) findViewById(R.id.btnTabMenu);
        btnTabMenu.setOnClickListener(OnClickListener);
        btnNotiMenu = (Button) findViewById(R.id.btnNotiMenu);
        btnNotiMenu.setOnClickListener(OnClickListener);
        
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
			case R.id.btnTabMenu:
//				int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
//				SlideoutActivity.prepare(MainActivity.this, R.id.vgMain, width);
//				startActivity(new Intent(MainActivity.this, LeftMenuActivity.class));
//				overridePendingTransition(0, 0);
				break;
			case R.id.btnNotiMenu:
				break;
			}
		}
	}; 
}
