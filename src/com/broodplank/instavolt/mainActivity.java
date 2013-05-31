package com.broodplank.instavolt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.broodplank.instavolt.R;
import com.stericson.RootTools.*;

@SuppressLint("SdCardPath")
public class mainActivity extends PreferenceActivity {

	public static final int MODE_WORLD_WRITEABLE = 0x00000002;

	public static final String VDD_LEVELS = "/sys/devices/system/cpu/cpu0/cpufreq/vdd_levels";
	public static final String MIN_VOLT_PREF = "lower_volt_mv";
	public static final String MAX_VOLT_PREF = "higher_volt_mv";
	public static final String CHECK_VOLT_PREF = "check_volt_mv";
	public static final String CREDITS_PREF = "pref_credits";


	private Preference mMinVoltPref;
	private Preference mMaxVoltPref;
	private Preference mCheckVoltPref;
	private Preference mCreditsPref;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RootTools.debugMode = true; // ON

		setTitle(R.string.app_name);
		addPreferencesFromResource(R.xml.instavolt);

		PreferenceScreen PrefScreen = getPreferenceScreen();

		mMinVoltPref = (Preference) PrefScreen.findPreference(MIN_VOLT_PREF);
		Intent i = new Intent(mainActivity.this, MIN.class);
		mMinVoltPref.setIntent(i);

		mMaxVoltPref = (Preference) PrefScreen.findPreference(MAX_VOLT_PREF);
		Intent i1 = new Intent(mainActivity.this, PLUS.class);
		mMaxVoltPref.setIntent(i1);

		 mCheckVoltPref = (Preference)
		 PrefScreen.findPreference(CHECK_VOLT_PREF);
		 Intent i2 = new Intent(mainActivity.this, CHECK.class);
		 mCheckVoltPref.setIntent(i2);
		 
		 mCreditsPref = (Preference) PrefScreen.findPreference(CREDITS_PREF);
	     Intent intent = new Intent(Intent.ACTION_VIEW);
	     intent.setData(Uri.parse("market://search?q=broodplank"));
	     mCreditsPref.setIntent(intent);

	}

	File file = new File(VDD_LEVELS);
	{
		if (file.exists()) {
			// Do action
			CommandCapture command = new CommandCapture(0,
					"busybox mount -o remount, rw /system",
					"busybox chmod 644 " + VDD_LEVELS, "busybox cp -f "
							+ VDD_LEVELS + " /sdcard/vdd_levels_backup");
			{
				try {

					RootTools.getShell(true).add(command).waitForFinish();

				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TimeoutException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else {
			Toast.makeText(
					getApplicationContext(),
					"It seems that your kernel does not support adjustable VDD levels...\nSwitch to a kernel that has VDD support and try again",
					Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public void onResume() {

		super.onResume();

	}

}
