package com.broodplank.instavolt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.broodplank.instavolt.R;
import com.stericson.RootTools.*;

@SuppressLint("SdCardPath")
public class mainActivity extends PreferenceActivity {

	public static final int MODE_WORLD_WRITEABLE = 0x00000002;

	public static final String VDD_LEVELS = "/sys/devices/system/cpu/cpu0/cpufreq/vdd_levels";
	public static final String UV_MV_TABLE = "/sys/devices/system/cpu/cpu0/cpufreq/UV_mV_table";

	public static final String MIN_VOLT_PREF = "lower_volt_mv";
	public static final String MAX_VOLT_PREF = "higher_volt_mv";
	public static final String CHECK_VOLT_PREF = "check_volt_mv";
	public static final String CREDITS_PREF = "pref_credits";
	// public static final String INITD_PREF = "set_initd";

	private Preference mMinVoltPref;
	private Preference mMaxVoltPref;
	private Preference mCheckVoltPref;
	// private Preference mSetInitdPref;
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

		mCheckVoltPref = (Preference) PrefScreen
				.findPreference(CHECK_VOLT_PREF);
		Intent i2 = new Intent(mainActivity.this, CHECK.class);
		mCheckVoltPref.setIntent(i2);

		// mSetInitdPref = (Preference) PrefScreen.findPreference(INITD_PREF);
		// Intent i3 = new Intent(mainActivity.this, INITD.class);
		// mSetInitdPref.setIntent(i3);
		//

		mCreditsPref = (Preference) PrefScreen.findPreference(CREDITS_PREF);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://search?q=broodplank"));
		mCreditsPref.setIntent(intent);

		CommandCapture command = new CommandCapture(0,
				"busybox mount -o remount, rw /system");
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

		File file = new File(VDD_LEVELS);
		File file2 = new File(UV_MV_TABLE);
		{
			if (file.exists()) {
				// Do action
				CommandCapture command1 = new CommandCapture(0,
						"busybox chmod 644 " + VDD_LEVELS, "busybox cp -f "
								+ VDD_LEVELS + " /sdcard/vdd_levels_backup");
				{
					try {

						RootTools.getShell(true).add(command1).waitForFinish();

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
				
			} else if (file2.exists()) {
				
				CommandCapture command1 = new CommandCapture(0,
						"busybox chmod 644 " + UV_MV_TABLE, "busybox cp -f "
								+ UV_MV_TABLE + " /sdcard/uv_mv_table_backup");
				{
					try {

						RootTools.getShell(true).add(command1).waitForFinish();

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
				
				PrefScreen.removeAll();
								
				super.onCreate(savedInstanceState);
				setContentView(R.layout.novdd);
				
				

			}

		}
	}

	@Override
	public void onResume() {

		super.onResume();

	}

}
