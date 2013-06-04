package com.broodplank.instavolt;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import android.os.Bundle;
import android.widget.Toast;
import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;



public class PLUS extends mainActivity {

    public static String VDD_LEVELS = "/sys/devices/system/cpu/cpu0/cpufreq/vdd_levels";
	public static final String UV_MV_TABLE = "/sys/devices/system/cpu/cpu0/cpufreq/UV_mV_table";


	
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(null);
	        	        
	        
	        SETDEF();
	        MAXMV();
		   PLUS.this.finish();

		}
		
		
		public void SETDEF() {
			File file = new File(VDD_LEVELS);
			File file2 = new File(UV_MV_TABLE);
			{
				if (file.exists()) {
					
					// then nothing since its default
					
				} else if (file2.exists()) {
					
					// set vdd_levels to UV_Mv_Table
				   VDD_LEVELS = UV_MV_TABLE;
						   
				   
				} else {
					

				    // then nothing since there is no compatibility for other names yet.
					// and thus this file will be never executed if that's the case.
				}
			}

			}
		
	
public void MAXMV() {
	

	
	 CommandCapture command = new CommandCapture(0, "busybox echo '+25' > "+VDD_LEVELS); {
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
		     "Increased voltage for all CPU frequencies by 25mV", Toast.LENGTH_LONG).show();     
	

 	 
}


    		 
     }

