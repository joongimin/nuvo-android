package com.xnuvo.code.main;

import com.xnuvo.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.support.v4.view.ViewPager;

public class MainActivity extends Activity {

	private Button btnTabMenu;
	private Button btnNotiMenu;

	private ViewPager vpList;
	private MainPagerAdapter vpListAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Layout initialize
        setContentView(R.layout.activity_main);
        
        // Layout variable connect  
        //// Menu
        btnTabMenu = (Button) findViewById(R.id.btnTabMenu);
        btnTabMenu.setOnClickListener(OnClickListener);
        btnNotiMenu = (Button) findViewById(R.id.btnNotiMenu);
        btnNotiMenu.setOnClickListener(OnClickListener);
        //// List
        vpList = (ViewPager) findViewById(R.id.vpList);
        vpListAdapter = new MainPagerAdapter(getApplicationContext(), 3, 15);
        vpList.setAdapter(vpListAdapter);
        vpList.setOnPageChangeListener(OnPageChangeListener);
//        vpList.setCurrentItem(vpListAdapter.getCount()/2-vpListAdapter.getCount()/2%vpCount);
        vpList.setCurrentItem(40);
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
	
	ViewPager.OnPageChangeListener OnPageChangeListener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageSelected(int position) {
			Log.i("Main", "selected: "+position);
		}
		@Override
		public void onPageScrolled(int position, float positionOffest, int positionOffsetPixels) {
//			Log.i("Main", position+" "+positionOffest+" "+positionOffsetPixels);
			if(positionOffest == 0.0) {
				if(position == 1) {
					vpList.setCurrentItem(vpListAdapter.getCircularFrontCount(), false);
				} else if(position == (vpListAdapter.getCount()-1)) {
					vpList.setCurrentItem(vpListAdapter.getCircularBackCount(), false);
				}
			}
//			if(positionOffest > 0.993) {
//				if(position == 5) {
//					vpList.setCurrentItem(3, false);
//				}
//////				if(vpList.getCurrentItem() == 2) {
////				if(position == 2) {
////					vpList.setCurrentItem(5, false);
////				} else if(position == 6) {
//////				} else if(vpList.getCurrentItem() == 6) {
////					vpList.setCurrentItem(3, false);
////				}
//			}
		}
		
		@Override
		public void onPageScrollStateChanged(int state) {
//			Log.i("Main", ""+state);
//			if(ViewPager.SCROLL_STATE_IDLE == state) {
//			}
		}
	}; 
}
