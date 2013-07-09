package com.xnuvo.code.main.tab;

import com.xnuvo.code.NuvoApplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabTitleView extends LinearLayout {
	
	private Context context;
	private TabMainView parent;
	public LinearLayout.LayoutParams parentParam;
	
	// Child view
	private int childIndex = 0;
	//// Display child count
	private final int displayChildCount = 3;
	private final int displayChildCountHalfRound = (int) Math.round(displayChildCount/2.0);
	//// Child's params
	private LinearLayout.LayoutParams childParam;
	//// Child item list
	private String[] childList;
	
	// Animation
	private int animationDuration = 250;
	private boolean isAnimationStarted = false; 
	
	public TabTitleView(Context context) {
		super(context);
		Initialize(context);
	}
	public TabTitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Initialize(context);
	}
	
	private void Initialize(Context context) {
		this.context = context;
		
		// Get child width size
		int width = NuvoApplication.widthPixels/displayChildCount;
        
//		// Set layout width size
//		LinearLayout.LayoutParams parentParam = new LinearLayout.LayoutParams(width*(displayChildCount+2), LinearLayout.LayoutParams.FILL_PARENT);
//		parentParam.leftMargin = -width; // Center align
//		setOrientation(LinearLayout.HORIZONTAL);
//		getChildCount();
//		setLayoutParams(parentParam);

		// Set child's width size
		childParam = new LayoutParams(width, LinearLayout.LayoutParams.FILL_PARENT);
		
		// Set childs
		for(int i=0; i<(displayChildCount+2); i++) {
			this.addView(setupChildView(new TextView(this.context)));
		}
	}
	
	public void moveLeft() {
		setAnimation(false);
		if(!parent.isVpForceMove || isAnimationStarted)
			parent.itemView.setCurrentItem(parent.itemView.getCurrentItem()-1, true);
		childIndex--;
		if(childIndex < 0)
			childIndex += childList.length;
	}
	public void moveRight() {
		setAnimation(true);
		if(!parent.isVpForceMove || isAnimationStarted)
			parent.itemView.setCurrentItem(parent.itemView.getCurrentItem()+1, true);
		childIndex++;
		childIndex %= childList.length;
	}
	
	OnClickListener childClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(!isAnimationStarted) {
				isAnimationStarted = true;
				parent.isVpForceMove = true;
				if(getChildAt(1) == v) {
					moveLeft();
				} else if(getChildAt(displayChildCount) == v) {
					moveRight();
				} else {
					isAnimationStarted = false;
				}
				parent.isVpForceMove = false;
			}
		}
	};
	
	private TextView setupChildView(TextView tv) {
		tv = new TextView(this.context);
		tv.setLayoutParams(childParam);
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(Color.WHITE);
		tv.setOnClickListener(childClickListener);
		return tv;
	}

	private int getCorrectIdx(int index) {
		if( index < 0) {
			return (childList.length + (index % childList.length)) % childList.length;	
		} else {
			return index % childList.length;
		}
	}
	
	public void setTextAll() {
		
		TextView tv;
		for(int i=-displayChildCountHalfRound; i<=displayChildCountHalfRound; i++) {
			tv = (TextView) getChildAt(i+displayChildCountHalfRound);
			tv.setTextColor(Color.LTGRAY);
			tv.setText("  "+childList[getCorrectIdx(childIndex+i)]);
			tv.setPaintFlags(tv.getPaintFlags());
//			tv.setText(Html.fromHtml("<font color=\"transparent\">● </font>"+this.childList[getCorrectIdx(defaultIdx+i)]+"<font></font><br/><font color=\"transparent\">　</font>"+this.childList[getCorrectIdx(defaultIdx+i)]+"<font></font>"));
		}
		tv = (TextView) getChildAt(displayChildCountHalfRound);
		tv.setTextColor(Color.WHITE);
		tv.setText(Html.fromHtml("<font color=\"red\">● </font>"+tv.getText()));
		tv.setPaintFlags(tv.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
	}
	
	public void setInfo(TabMainView parent, String[] childList, int defaultIdx) { // , ViewGroup.LayoutParams titleImageParams
		this.parent = parent;
		
		//// Set layout width size
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
		if(params == null) {
			params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		}
		params.leftMargin = -childParam.width; // Center align
		setOrientation(LinearLayout.HORIZONTAL);
		getChildCount();
		setLayoutParams(params);
		
		this.childList = childList;
		this.childIndex = defaultIdx;
		setTextAll();
	}
	
	private void setAnimation(final boolean isLeft) {
		Animation animation = new TranslateAnimation(0, childParam.width*(isLeft?-1:1), 0, 0);
		animation.setDuration(animationDuration);
		animation.setInterpolator(AnimationUtils.loadInterpolator(context, android.R.anim.accelerate_decelerate_interpolator));

		animation.setFillAfter(true);

		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
				int idx = isLeft ? 0 : displayChildCount+1;
				int addIdx = isLeft ? displayChildCount+1 : 0;

				removeViewAt(idx);
				
				Log.i("TTL", "This center idx is : "+childIndex);
				TextView tv = setupChildView(new TextView(context));
				tv.setText(childList[getCorrectIdx(childIndex + (isLeft ? displayChildCountHalfRound : -displayChildCountHalfRound))]);
				tv.setOnClickListener(childClickListener);
				
				addView(tv, addIdx);

				animation.setFillAfter(false);
				clearAnimation();
				
				setTextAll();
				
				isAnimationStarted = false;
			}
		});
		
		for (int i = 0; i < this.getChildCount(); i++) {
			getChildAt(i).startAnimation(animation);
		}
	}
	

}
