package com.korovyansk.android.sample.slideout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import com.korovyansk.android.slideout.SlideoutActivity;

public class SampleActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample);
//		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
//	    	getActionBar().hide();
//	    }
		findViewById(R.id.left_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
						SlideoutActivity.prepare(SampleActivity.this, R.id.inner_content, width);
						startActivity(new Intent(SampleActivity.this, LeftMenuActivity.class));
						overridePendingTransition(0, 0);
					}
				});
		findViewById(R.id.right_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
						SlideoutActivity.prepare(SampleActivity.this, R.id.inner_content, width);
						startActivity(new Intent(SampleActivity.this, RightMenuActivity.class));
						overridePendingTransition(0, 0);
					}
				});
	}


}
