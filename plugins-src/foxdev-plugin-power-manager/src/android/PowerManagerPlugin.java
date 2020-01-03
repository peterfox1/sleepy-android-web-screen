package org.apache.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Context;
import android.os.PowerManager;

import android.view.WindowManager;

import android.app.Activity;
import android.util.Log;
import android.os.PowerManager.WakeLock;
import java.util.concurrent.TimeUnit;


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

		this.wakeDevice();

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
		
		this.turnScreenOnThroughKeyguard(cordova.getActivity());
		
	}
	
	/**
	 * Attempt a few methods of waking the screen!
	 * https://stackoverflow.com/a/52403450
	 */
	public void turnScreenOnThroughKeyguard(Activity activity) {
		this.userPowerManagerWakeup(activity);
		//this.useWindowFlags(activity);	// Throws exception.
		this.useActivityScreenMethods(activity);
	}

	private void useActivityScreenMethods(Activity activity) {
		activity.setTurnScreenOn(true);
		activity.setShowWhenLocked(true);
	}

	private void useWindowFlags(Activity activity) {
		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
	}

	private void userPowerManagerWakeup(Activity activity) {
		PowerManager pm = (PowerManager) this.cordova.getActivity().getApplicationContext().getSystemService(Context.POWER_SERVICE);
		WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "myapp:taggy");
		wakeLock.acquire(TimeUnit.SECONDS.toMillis(5));
	}


}
