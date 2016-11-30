package com.incore.belight.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyData {
	private static MyData mInstance;
	private Context mCtx;
	private SharedPreferences p;
	private String username, password;
	private int balance = -1;

	private MyData(Context c) {
		mCtx = c.getApplicationContext();
		p = getSharedPreferences();
	}

	public static MyData getInstance(Context c) {
		if (mInstance == null) {
			mInstance = new MyData(c);
		}
		return mInstance;
	}

	public SharedPreferences getSharedPreferences() {
		if (p == null) {
			PreferenceManager.getDefaultSharedPreferences(mCtx);
		}
		return p;
	}

	public String getUsername() {
		if (username == null) {
			username = p.getString("username", "NO_USERNAME_FOUND");
		}
		return username;
	}

	public String getPassword() {
		if (password == null) {
			password = p.getString("password", "NO_PASSWORD_FOUND");
		}
		return password;
	}

	public int getBalance() {
		if (balance == -1) {
			balance = p.getInt("balance", 0);
		}
		return balance;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void presistData() {
		p.edit().putString("username", username).putString("password", password).putInt("balance", balance).apply();
	}
}
