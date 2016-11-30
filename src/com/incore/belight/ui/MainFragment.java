package com.incore.belight.ui;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.incore.belight.R;
import com.incore.belight.data.MyData;
import com.incore.belight.internet.MyServer;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainFragment extends Fragment {
	private FragmentsCallbacks mCallbacks;

	private FloatingActionsMenu fam;
	private TextView tvBalance;

	public MainFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		ToggleButton tb = (ToggleButton) rootView.findViewById(R.id.toggleButton1);
		tvBalance = (TextView) rootView.findViewById(R.id.tvBalance);
		tvBalance.setText("" + MyData.getInstance(getActivity()).getBalance() + " S.P");
		fam = (FloatingActionsMenu) rootView.findViewById(R.id.fabMainActions);
		tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					hideFABs();
				} else {
					showFABs();
				}
			}

		});
		return rootView;

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (FragmentsCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException("Activity must implement MainFragmentCallbacks");
		}
	}

	private void showFABs() {
		fam.setVisibility(View.VISIBLE);
		fam.animate().translationX(0).translationY(0).setDuration(1000).setStartDelay(500).setListener(null).start();
	}

	private void hideFABs() {
		AnimatorListener l = new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				fam.collapse();
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				fam.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}
		};
		fam.animate().translationX(30).translationY(150).setDuration(1500).setStartDelay(500).setListener(l).start();

	}
}
