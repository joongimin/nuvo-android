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
	
	private TabBarView tabBar;
	private String[] tabBarList = new String[]{"뉴스 피드", "작품", "작가"};

	public ViewPager vpList;
	public boolean isVpForceMove = false;
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
        //// TabBar
        tabBar = (TabBarView) findViewById(R.id.tabbar);
        tabBar.setInfo(tabBarList, 0);
        //// List
        vpList = (ViewPager) findViewById(R.id.vpList);
        vpListAdapter = new MainPagerAdapter(getApplicationContext(), tabBarList.length, 15);
        vpList.setAdapter(vpListAdapter);
        vpList.setCurrentItem(vpListAdapter.getCount()/tabBarList.length*2);
        vpList.setOnPageChangeListener(OnPageChangeListener);
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
		int beforePosition;
		@Override
		public void onPageSelected(int position) {
//			Log.i("Main", "selected: "+position);
			if(!isVpForceMove) {
				isVpForceMove = true;
				if(position < beforePosition) {
					tabBar.moveLeft();
				} else {
					tabBar.moveRight();
				}
				isVpForceMove = false;
			}
		}
		@Override
		public void onPageScrolled(int position, float positionOffest, int positionOffsetPixels) {
//			Log.i("Main", position+" "+positionOffest+" "+positionOffsetPixels);
			if(positionOffest == 0.0) {
				isVpForceMove = true;
				if(position == 1) {
					vpList.setCurrentItem(vpListAdapter.getCircularFrontCount(), false);
				} else if(position == (vpListAdapter.getCount()-1)) {
					vpList.setCurrentItem(vpListAdapter.getCircularBackCount(), false);
				}
				isVpForceMove = false;
			}
		}
		@Override
		public void onPageScrollStateChanged(int state) {
//			Log.i("Main", ""+state);
			if(ViewPager.SCROLL_STATE_DRAGGING == state) {
				beforePosition = vpList.getCurrentItem();
			}
		}
	}; 
}
