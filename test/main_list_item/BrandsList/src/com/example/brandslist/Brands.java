package com.example.brandslist;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Brands {
	ArrayList<Brand> brands;
	public class Brand {
		// JSon Object
		public long brand_id;
		public String profile_picture;
		public String brand_name;
		public String brand_description;
		public String brand_uri;
	    public String category_name;
	    public String user_name;
	    public int products_length;
	    public int adores_length;
	    public long adore_id;
		// Additional Object
		public Bitmap image;
	}
}
