package com.example.brandslist;

import java.util.ArrayList;

import com.example.brandslist.Brands.Brand;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BrandsAdpater extends BaseAdapter {
	
	private Context context;
	private LayoutInflater inflater;
	
	private BrandViewHolder holder;

	private ArrayList<Brand> brandList;
	private Brand brand;
	
	public BrandsAdpater(Context context) {
		this.context = context;
		this.inflater = ((Activity)context).getLayoutInflater();
		this.brandList = new ArrayList<Brands.Brand>();
	}

	public void addItem(Brand p) {
		brandList.add(p);
	}
	public void addItem(ArrayList<Brand> pl) {
		for(Brand p : pl)
			brandList.add(p);
	}

	@Override
	public int getCount() {
		return brandList.size();
	}

	@Override
	public Object getItem(int position) {
		return brandList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	int a = 0;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_brand, null);
			
			holder = new BrandViewHolder();

			holder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
			holder.ivLikeHeart = (ImageView) convertView.findViewById(R.id.ivLikeHeart);
			holder.ivLikeHeart.setId(position);
			holder.ivLikeHeart.setOnClickListener(onClickListener);
			holder.tvBrandName = (TextView) convertView.findViewById(R.id.tvBrandName);
			holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
			holder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
			holder.tvProductsLength = (TextView) convertView.findViewById(R.id.tvProductsLength);
			holder.tvAdoresLength = (TextView) convertView.findViewById(R.id.tvAdoresLength);
			
			convertView.setTag(holder);
		} else {
			holder = (BrandViewHolder) convertView.getTag();
		}
		
		brand = brandList.get(position);
//		holder.ivImage.setImageResource(R.drawable.brand_image);
		holder.ivLikeHeart.setImageResource(R.drawable.like_heart);
		holder.ivLikeHeart.setBackgroundColor(Color.RED);
		holder.tvBrandName.setText(brand.brand_name);
		holder.tvUserName.setText(brand.user_name);
		holder.tvDescription.setText(brand.brand_description);
		holder.tvProductsLength.setText(""+brand.products_length);
		holder.tvAdoresLength.setText(""+brand.adores_length);

		convertView.setClickable(false);
		return convertView;
	}
	
	OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.i("ML", ""+v.getId());
		}
	};
	
	private class BrandViewHolder {
		public ImageView ivImage;
		public ImageView ivLikeHeart;
		public TextView tvBrandName;
		public TextView tvUserName;
		public TextView tvDescription;
		public TextView tvProductsLength;
		public TextView tvAdoresLength;
	}

}
