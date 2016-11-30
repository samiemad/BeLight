package com.incore.belight.ui;

import com.incore.belight.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AccountFragment extends Fragment {
	private FragmentsCallbacks mCallbacks;

	public AccountFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_account, container, false);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (FragmentsCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException("Activity must implement AccountFragmentCallbacks");
		}
	}

}
