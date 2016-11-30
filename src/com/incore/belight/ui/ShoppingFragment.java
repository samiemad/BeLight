package com.incore.belight.ui;

import com.incore.belight.R;
import com.incore.belight.data.MyData;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShoppingFragment extends Fragment {
	private FragmentsCallbacks mCallbacks;
	private TextView tvBalance;

	public ShoppingFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_shopping, container, false);
		tvBalance = (TextView) rootView.findViewById(R.id.tvBalance);
		tvBalance.setText("" + MyData.getInstance(getActivity()).getBalance() + " S.P");

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (FragmentsCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException("Activity must implement ShoppingFragmentCallbacks");
		}
	}
}
