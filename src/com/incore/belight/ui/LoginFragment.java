package com.incore.belight.ui;

import com.incore.belight.R;
import com.incore.belight.data.MyData;
import com.incore.belight.internet.MyServer;
import com.incore.belight.internet.MyServer.ServerCallbacks;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment implements OnClickListener {
	private FragmentsCallbacks mCallbacks;
	private EditText etUsername, etPassword;
	Button bLogin;

	public LoginFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_login, container, false);
		bLogin = (Button) rootView.findViewById(R.id.bLogin);
		etUsername = (EditText) rootView.findViewById(R.id.etUsername);
		etPassword = (EditText) rootView.findViewById(R.id.etPassword);
		bLogin.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.bLogin) {
			bLogin.setEnabled(false);
			bLogin.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.presence_away, 0, 0, 0);
			MyData.getInstance(getActivity()).setUsername(etUsername.getText().toString());
			MyData.getInstance(getActivity()).setPassword(etPassword.getText().toString());
			MyServer.getBalance(getActivity(), new ServerCallbacks() {
				@Override
				public void onSuccess(int balance) {
					MyData.getInstance(getActivity()).setBalance(balance);
					mCallbacks.loginCompleted();
				}

				@Override
				public void onError(String msg) {
					AlertDialog dlg = new AlertDialog.Builder(getActivity()).create();
					dlg.setTitle("Login Error!");
					dlg.setCanceledOnTouchOutside(false);
					dlg.setIcon(android.R.drawable.presence_busy);
					dlg.setMessage("The following error occured during login:\n" + msg + "\nPress back to try again.");
					dlg.setButton(AlertDialog.BUTTON_NEUTRAL, "Back", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							bLogin.setEnabled(true);
							bLogin.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_secure, 0);
							dialog.dismiss();
						}
					});
					dlg.setButton(DialogInterface.BUTTON_NEGATIVE, "Exit BeLight",
							new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							getActivity().finish();
						}
					});
					dlg.show();
					Toast.makeText(getActivity(), "Error Logging in : " + msg, Toast.LENGTH_LONG).show();
				}
			});
		}
	}
}
