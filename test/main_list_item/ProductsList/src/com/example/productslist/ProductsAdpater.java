package com.example.productslist;

import java.util.ArrayList;

import com.example.productslist.Products.Product;

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

public class ProductsAdpater extends BaseAdapter {
	
	private Context context;
	private LayoutInflater inflater;
	
	private ProductViewHolder holder;

	private ArrayList<Product> productList;
	private Product product;
	
	public ProductsAdpater(Context context) {
		this.context = context;
		this.inflater = ((Activity)context).getLayoutInflater();
		this.productList = new ArrayList<Products.Product>();
	}

	public void addItem(Product p) {
		productList.add(p);
	}
	public void addItem(ArrayList<Product> pl) {
		for(Product p : pl)
			productList.add(p);
	}

	@Override
	public int getCount() {
		return productList.size();
	}

	@Override
	public Object getItem(int position) {
		return productList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	int a = 0;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_product, null);
			
			holder = new ProductViewHolder();
			holder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
			holder.ivLikeHeart = (ImageView) convertView.findViewById(R.id.ivLikeHeart);
			holder.ivLikeHeart.setId(position);
			holder.ivLikeHeart.setOnClickListener(onClickListener);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
			
			convertView.setTag(holder);
		} else {
			holder = (ProductViewHolder) convertView.getTag();
		}
		
		product = productList.get(position);
		holder.ivImage.setImageResource(R.drawable.test);
		holder.ivLikeHeart.setImageResource(R.drawable.like_heart);
		holder.ivLikeHeart.setBackgroundColor(Color.RED);
		holder.tvName.setText((a++)+product.name);
		holder.tvPrice.setText("\\"+product.price);

		convertView.setClickable(false);
		return convertView;
	}
	
	OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.i("ML", ""+v.getId());
		}
	};
	
	private class ProductViewHolder {
		public ImageView ivImage;
		public ImageView ivLikeHeart;
		public TextView tvName;
		public TextView tvPrice;
	}

}
