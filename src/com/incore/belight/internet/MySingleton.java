package com.incore.belight.internet;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.content.Context;

public class MySingleton {

	private static MySingleton mInstance;
	private RequestQueue mRequestQueue;
	private static Context mCtx;

	private MySingleton(Context c) {
		mCtx = c;
		mRequestQueue = getRequestQueue();

	}

	public static synchronized MySingleton getInstance(Context c) {
		if (mInstance == null) {
			mInstance = new MySingleton(c);
		}
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}
}
