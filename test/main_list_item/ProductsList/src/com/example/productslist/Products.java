package com.example.productslist;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Products {
	ArrayList<Product> products;
	public class Product {
		// JSon Object
		public long id;
		public String profile_picture_url;
		public String small_profile_picture_url;
		public String name;
		public long price;
		public long adore_id;
		// Additional Object
		public Bitmap image;
	}
}
