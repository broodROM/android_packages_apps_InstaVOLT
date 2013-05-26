package com.broodplank.instavolt;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

    private Preference mVoltMin;
    private Preference mVoltPlus;

    
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	RootTools.debugMode = true; //ON
    	
        
        
        setTitle(R.string.app_name);
        addPreferencesFromResource(R.xml.instavolt);

        PreferenceScreen PrefScreen = getPreferenceScreen();
        

        
        CommandCapture command = new CommandCapture(0, "busybox mount -o remount, rw /system", "busybox chmod 644 "+VDD_LEVELS, "busybox cp -f "+VDD_LEVELS+" /sdcard/vdd_levels_backup"); {
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
        
     }
     
    
    
    

    @Override
    public void onResume() {
        String temp;

        super.onResume();



    }
    


		

} 
    
    
