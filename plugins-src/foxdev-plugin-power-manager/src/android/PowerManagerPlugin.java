package org.apache.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;

/**
* This class echoes a string called from JavaScript.
*/
public class PowerManagerPlugin extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("wakeDevice")) {
			this.wakeDevice(args, callbackContext);
			return true;
		}
		return false;
	}
	
	private void wakeDevice(JSONArray args, CallbackContext callbackContext) {

		//this.wakeDevice();

		AlertDialog.Builder builder1 = new AlertDialog.Builder(this.cordova.getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
		builder1.setMessage("Write your message here.");
		builder1.setCancelable(true);

		builder1.setPositiveButton(
				"Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				}
		);

		builder1.setNegativeButton(
				"No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				}
		);

		AlertDialog alert11 = builder1.create();
		alert11.show();

		// String message = args.getString(0);
		//
		// if (message != null && message.length() > 0) {
		// 	callbackContext.success(message);
		// } else {
		// 	callbackContext.error("Expected one non-empty string argument.");
		// }
	}

	public void wakeDevice() {
//		PowerManager powerManager = (PowerManager) getSystemService(this.cordova.getActivity().POWER_SERVICE);
//		wakeLock = powerManager.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
//		wakeLock.acquire();
//
//		KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
//		KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("TAG");
//		keyguardLock.disableKeyguard();
//		runOnUiThread(new Runnable(){
//			public void run(){
//				getWindow().addFlags(
//						WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//								| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
//								| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//								| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
//			}
//		});
	}

}
