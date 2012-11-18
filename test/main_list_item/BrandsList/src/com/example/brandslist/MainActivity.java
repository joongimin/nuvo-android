package com.example.brandslist;

import org.apache.http.client.methods.HttpGet;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MainActivity extends Activity {

	HttpManager http;
	
	private ListView lvList;
	private BrandsAdpater brandsAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lvList = (ListView) findViewById(R.id.lvBrands);
        
        http = new HttpManager();
        new Thread(new Runnable() {
			@Override
			public void run() {
				String data = http.request(new HttpGet("http://xnuvo.com/api/v1/brands.json?product_category_id=0&sort=0"));
				
				Gson gson = new Gson();
				final Brands bList = gson.fromJson(data, Brands.class);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
				        
				        LayoutInflater inflater = getLayoutInflater();
				        View v = inflater.inflate(R.layout.header_brand, null);
				        lvList.addHeaderView(v);	
				        
				        brandsAdapter = new BrandsAdpater(MainActivity.this);
				        lvList.setAdapter(brandsAdapter);
				        
						brandsAdapter.addItem(bList.brands);
						brandsAdapter.notifyDataSetChanged();	
				        lvList.setSelectionFromTop(1, 0);
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
