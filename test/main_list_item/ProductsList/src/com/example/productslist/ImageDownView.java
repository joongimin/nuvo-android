package com.example.productslist;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ImageDownView extends ImageView {

	public ImageDownView(Context context) {
		super(context);
	}

	public ImageDownView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ImageDownView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void setImageDownload(String url) {
		
	}
	
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // Call super() so that resolveUri() is called.
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // If there's no drawable we can just use the result from super.
        if (getDrawable() == null)
            return;

        final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        int w = getDrawable().getIntrinsicWidth();
        int h = getDrawable().getIntrinsicHeight();
        if (w <= 0)
            w = 1;
        if (h <= 0)
            h = 1;

        // Desired aspect ratio of the view's contents (not including padding)
        float desiredAspect = (float) w / (float) h;

        // We are allowed to change the view's width
        boolean resizeWidth = widthSpecMode != MeasureSpec.EXACTLY;

        // We are allowed to change the view's height
        boolean resizeHeight = heightSpecMode != MeasureSpec.EXACTLY;

        int pleft = getPaddingLeft();
        int pright = getPaddingRight();
        int ptop = getPaddingTop();
        int pbottom = getPaddingBottom();

        // Get the sizes that ImageView decided on.
        int widthSize = getMeasuredWidth();
        int heightSize = getMeasuredHeight();

        if (resizeWidth && !resizeHeight)
        {
            // Resize the width to the height, maintaining aspect ratio.
            int newWidth = (int) (desiredAspect * (heightSize - ptop - pbottom)) + pleft + pright;
            setMeasuredDimension(newWidth, heightSize);
        }
        else if (resizeHeight && !resizeWidth)
        {
            int newHeight = (int) ((widthSize - pleft - pright) / desiredAspect) + ptop + pbottom;
            setMeasuredDimension(widthSize, newHeight);
        }
    }
}
