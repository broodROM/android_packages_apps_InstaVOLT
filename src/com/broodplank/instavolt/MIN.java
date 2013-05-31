package com.broodplank.instavolt;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import android.os.Bundle;
import android.widget.Toast;
import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;

public class MIN extends mainActivity {

	public static final String VDD_LEVELS = "/sys/devices/system/cpu/cpu0/cpufreq/vdd_levels";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(null);

		MINMV();

		MIN.this.finish();

	}

	public void MINMV() {

		CommandCapture command = new CommandCapture(0, "busybox echo '-25' > "
				+ VDD_LEVELS);
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

		Toast.makeText(getApplicationContext(),
				"Decreased voltage for all CPU frequencies by 25mV",
				Toast.LENGTH_LONG).show();

	}

}
