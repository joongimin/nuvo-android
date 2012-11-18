package com.example.productslist;


import org.apache.http.client.methods.HttpGet;

import com.google.gson.Gson;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;

public class MainActivity extends Activity {

	private HttpManager http;
	
	private Handler handler;
	
	private GridView gvList;
	private ProductsAdpater productsAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gvList = (GridView) findViewById(R.id.gvList);
        productsAdapter = new ProductsAdpater(this);
        gvList.setAdapter(productsAdapter);
        
        http = new HttpManager();
        new Thread(new Runnable() {
			@Override
			public void run() {
				String data = http.request(new HttpGet("http://xnuvo.com/api/v1/products.json?product_category_id=0&offset=0&sort=0"));
				
				Gson gson = new Gson();
				final Products pList = gson.fromJson(data, Products.class);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						productsAdapter.addItem(pList.products);
						productsAdapter.notifyDataSetChanged();	
					}
				});
			}
		}).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
