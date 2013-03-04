package pl.qbasso.videorecorder;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class ApplicationPreferences extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SharedPreferences preference = PreferenceManager
				.getDefaultSharedPreferences(this);
		addPreferencesFromResource(R.xml.preferences);
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		preference.registerOnSharedPreferenceChangeListener(this);
	}
//
//	private void setEmailSummary(SharedPreferences preference) {
//		Spannable summary = new SpannableString(preference.getString(
//				"email_addr", ""));
//		summary.setSpan(new ForegroundColorSpan(Color.BLUE), 0,
//				summary.length(), 0);
//		mEmailPreference.setSummary(summary);
//	}
//
//	private void setServerSummary(SharedPreferences preference) {
//		Spannable summary = new SpannableString(preference.getString(
//				"server_addr", ""));
//		summary.setSpan(new ForegroundColorSpan(Color.BLUE), 0,
//				summary.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//		mServerPreference.setSummary(summary);
//	}
//
//	@Override
//	public boolean onPreferenceChange(Preference preference, Object newValue) {
//		if (preference.getKey().equals("email_addr")) {
//			if (Utils.validateEmail((String) newValue)) {
//				return true;
//			} else {
//				Utils.showToastMessage(this, "Wrong email address! Try again");
//			}
//		} else {
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
//			String key) {
//		}
//	}
//
//	@Override
//	protected void onPause() {
//		super.onPause();
//		PreferenceManager.getDefaultSharedPreferences(this)
//				.unregisterOnSharedPreferenceChangeListener(this);
//	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

}
