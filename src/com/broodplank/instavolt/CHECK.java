package com.broodplank.instavolt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;




public class CHECK extends mainActivity {

	
    public static final String VDD_LEVELS = "/sys/devices/system/cpu/cpu0/cpufreq/vdd_levels";

		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(null);
	        
	       
	        
	        CHECKNOW();
		}
		
		

public static String readOneLine(String fname) {
    BufferedReader br;
    String line = null;

    try {
        br = new BufferedReader(new FileReader(fname), 512);
        try {
            line = br.readLine();
        } finally {
            br.close();
        }
    } catch (Exception e) {
    }
    return line;
}

public void CHECKNOW() {
	
	CommandCapture command = new CommandCapture(0, "cat "+VDD_LEVELS+" > /sdcard/vdd_levels"); {
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
	
final AlertDialog.Builder builder1=new AlertDialog.Builder(this);
builder1.setTitle("VDD Levels (Hz: mV)");
builder1.setIcon(android.R.drawable.ic_dialog_alert);


  final AlertDialog.Builder builder=new AlertDialog.Builder(this);
     builder.setTitle("VDD Levels (Hz: mV)");
     builder.setIcon(android.R.drawable.ic_dialog_alert);
     
     try {
	 		 BufferedReader r = new BufferedReader(new FileReader("/storage/sdcard0/vdd_levels"));
	 		 StringBuilder total = new StringBuilder();
	 		 String line;
	 		 while((line = r.readLine()) != null) {
	 		     total.append(line+"\n");
		 	     
	 	 	      builder.setMessage(total);
	 	 	      
	 		 }
	    } catch (IOException ex) {
	    	
	    	}
     
    builder.setPositiveButton("OK", new OnClickListener() {
   	 
   	 
   	 

   	   @Override
   	   public void onClick(DialogInterface dialog, int which) {
   		   
   		   
   		   CommandCapture command = new CommandCapture(0, "cat "+VDD_LEVELS+" > /sdcard/vdd_levels"); {
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
   	
		 	    	
   		 	 try {
   		 		 BufferedReader r = new BufferedReader(new FileReader("/storage/sdcard0/vdd_levels"));
   		 		 StringBuilder total = new StringBuilder();
   		 		 String line;
   		 		 while((line = r.readLine()) != null) {
   		 		     total.append(line+"\n");
   			 	     
   		 	 	      builder.setMessage(total);
   		 	 	      
   		 		 }
   		    } catch (IOException ex) {
   		    	
   		    	}
   			     builder1.setPositiveButton("OK", new OnClickListener() {	
   			     
   			     @Override
   			     public void onClick(DialogInterface dialog, int which) {

   			     }
   			    });
   			    	   	
   		
   		   }}});
    

    builder.setNegativeButton("Cancel", new OnClickListener() {

   	   @Override
   	   public void onClick(DialogInterface dialog, int which) {
   		   
   		   CommandCapture command2 = new CommandCapture(0, "rm /sdcard/vdd_levels"); {
   		  		try {
   		  	
   		  			RootTools.getShell(true).add(command2).waitForFinish();
   		  		

   		  		} catch (InterruptedException e3) {
   		  			// TODO Auto-generated catch block
   		  			e3.printStackTrace();
   		  		} catch (IOException e3) {
   		  			// TODO Auto-generated catch block
   		  			e3.printStackTrace();
   		  		} catch (TimeoutException e3) {
   		  			// TODO Auto-generated catch block
   		  			e3.printStackTrace();
   		  		}
   		      }
   	    // TODO Auto-generated method stub

   	   }
   	  });

builder.show();
	
}
	
}}




	



