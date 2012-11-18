package com.example.brandslist;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpManager {

	public final static int HTTP_METHOD_DELETE = 1; 

	protected HttpClient mHttpClient;

	public HttpManager() {
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		
		mHttpClient = new DefaultHttpClient(params);
	}
	
	/*
	// execute a GET-request and get the response as a String
	final String response = 
	    Http.get("http://somesite.com")
	        .use(client)                // use this HttpClient (required)
	        .charset("UTF-8")           // set the encoding... (optional)
	        .followRedirects(true)      // ...follow redirects (optional)
	        .asString();                // execute request
	*/
	
	public String request(HttpRequestBase method) {
		
		String result = "";
		
		try {

			method.addHeader("Accept-Encoding", "gzip,deflate");

			HttpResponse response = mHttpClient.execute(method);
			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {

				// Get html page
				InputStream is = resEntity.getContent();
				
				// Check gzip
				Header header = response.getFirstHeader("Content-Encoding");
				if (header != null && header.getValue().equalsIgnoreCase("gzip")) {
					is = new GZIPInputStream(is);
				}

				StringBuffer sb = new StringBuffer();
				byte[] b = new byte[4096];
				for (int n; (n = is.read(b)) != -1;) {
					sb.append(new String(b, 0, n));
				}

				result = sb.toString();
			}
	        
		} catch (Exception e) {
			System.out.println("Error Url is "+method.getURI());
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
}