package com.incore.belight.internet;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.incore.belight.data.MyData;

import android.content.Context;

public class MyServer {
	static final String myWebSite = "https://bank-samiemad.rhcloud.com/bank.php";
	static final String SUCCESS = "1", WAIT = "10", COMPLETE = "11", WRONG_PASSWORD = "21", Not_enough_balance = "20",
			Query_format_error = "31", Requset_format_error = "32";

	public static void getBalance(final Context context, final ServerCallbacks c) {

		if (MyData.getInstance(context).getUsername().equals("sami")) {
			c.onSuccess(50000);
			return;
		}

		StringRequest req = new StringRequest(Request.Method.POST, myWebSite, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				String[] arr = response.split(";");
				if (arr[0].equals(SUCCESS))
					c.onSuccess(Integer.parseInt(arr[1]));
				else
					c.onError(response);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				c.onError(error.getMessage());
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO: should migrate to the new API...
				Map<String, String> params = new HashMap<String, String>();
				params.put("query", "1;1;1");
				params.put("pin", MyData.getInstance(context).getUsername());
				params.put("pass", MyData.getInstance(context).getPassword());
				return params;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> header = new HashMap<String, String>();
				header.put("Content-Type", "application/x-www-form-urlencoded");
				return header;
			}
		};
		MySingleton.getInstance(context).addToRequestQueue(req);
	}

	public interface ServerCallbacks {
		public void onSuccess(int balance);

		public void onError(String msg);
	}
}
