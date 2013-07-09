package com.xnuvo.code.main.tab;

import com.xnuvo.R;
import com.xnuvo.code.NuvoApplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TabMainView extends RelativeLayout {

	public TabMainView(Context context) {
		super(context);
		Initialize(context);
	}

	public TabMainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Initialize(context);
	}
	
	private Context context;

	private final int CHILD_VIEW_TITLE = 1;
	private final int CHILD_VIEW_TITLE_IMAGE = 2;
	private final int CHILD_VIEW_ITEM = 3;  

	private TabTitleView titleView;
	private ImageView titleImage;
	
	private TabItemAdapter itemAdapter;
	public boolean isVpForceMove = false;
	public TabMainViewPager itemView;
	
	private String[] tabBarList = new String[]{"작품", "작가", "뉴스 피드"};
	
	private void Initialize(Context context) {
		this.context = context;
		
        // View information
		setBackgroundColor(Color.BLACK);
        
        // Child Views
		titleImage = new ImageView(context);
		titleImage.setId(CHILD_VIEW_TITLE_IMAGE);
		titleImage.setImageResource(R.drawable.tapbar);
		titleImage.setAdjustViewBounds(true);
		titleImage.setScaleType(ScaleType.FIT_XY); // setAdjustViewBounds should be called before setScaleType
		RelativeLayout.LayoutParams titleImageParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        titleImage.setLayoutParams(titleImageParams);
		
		titleView = new TabTitleView(context);
		titleView.setId(CHILD_VIEW_TITLE);
        titleView.setInfo(this, tabBarList, 0);
        titleView.setBackgroundColor(Color.TRANSPARENT);
        RelativeLayout.LayoutParams titleParams = (RelativeLayout.LayoutParams) titleView.getLayoutParams();
        titleParams.addRule(RelativeLayout.ALIGN_TOP, CHILD_VIEW_TITLE_IMAGE);
        titleParams.addRule(RelativeLayout.ALIGN_BOTTOM, CHILD_VIEW_TITLE_IMAGE);
        titleView.setLayoutParams(titleParams);

        itemAdapter = new TabItemAdapter(context, tabBarList.length, 15);

        itemView = new TabMainViewPager(context);
        itemView.setId(CHILD_VIEW_ITEM);
        itemView.setAdapter(itemAdapter);
        itemView.setCurrentItem(itemAdapter.getCount()/tabBarList.length*2);
        itemView.setOnPageChangeListener(OnPageChangeListener);
        RelativeLayout.LayoutParams itemViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        itemViewParams.addRule(RelativeLayout.BELOW, CHILD_VIEW_TITLE);
        itemView.setLayoutParams(itemViewParams);

        addView(titleImage);
        addView(titleView);
        addView(itemView);
	}	
	
	ViewPager.OnPageChangeListener OnPageChangeListener = new ViewPager.OnPageChangeListener() {
		int beforePosition;
		@Override
		public void onPageSelected(int position) {
//			Log.i("Main", "selected: "+position);
			if(!isVpForceMove) {
				isVpForceMove = true;
				if(position < beforePosition) {
					titleView.moveLeft();
				} else {
					titleView.moveRight();
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
					itemView.setCurrentItem(itemAdapter.getCircularFrontCount(), false);
				} else if(position == (itemAdapter.getCount()-1)) {
					itemView.setCurrentItem(itemAdapter.getCircularBackCount(), false);
				}
				isVpForceMove = false;
			}
		}
		@Override
		public void onPageScrollStateChanged(int state) {
//			Log.i("Main", ""+state);
			if(ViewPager.SCROLL_STATE_DRAGGING == state) {
				beforePosition = itemView.getCurrentItem();
			}
		}
	}; 
	
}
