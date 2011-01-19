package com.schneenet.cardstack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.MotionEvent;
import android.view.View;

public class CardView extends View implements SensorEventListener {

	public CardView(Context context) {
		super(context);
		mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mPowerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, this.getClass().getName());
	}
	
	public void onPause() {
		mWakeLock.release();
		mSensorManager.unregisterListener(this);
	}
	
	public void onResume() {
		mWakeLock.acquire();
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}

	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private PowerManager mPowerManager;
	private WakeLock mWakeLock;
	
	private double mCardRotation;
	private float mCardSize;
	private float mCardWidth;
	private float mCardHeight;
	
	private Bitmap mDisplayedCard;

	@Override
	public void onDraw(Canvas c) {
		mCardWidth = mDisplayedCard.getWidth() * mCardSize;
		mCardHeight = mDisplayedCard.getWidth() * mCardSize;
		
		float x = c.getWidth() / 2;
		float y = c.getHeight() / 2;
		
		Matrix mMatrix = new Matrix();
		
		c.drawBitmap(mDisplayedCard, mMatrix, null);
		
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		// Ensure we are an event from the Accelerometer
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			// Handle Accelerometer orientation change 
			double x = event.values[0];
			double y = event.values[1];
			if (x == 0) {
				mCardRotation = 0;
			} else {
				mCardRotation = Math.tan(y / x);
			}
			this.invalidate();
		}		
	}
	
	@Override
	public boolean onTouchEvent(final MotionEvent e) {
		//TODO Handle swipe gestures, two finger zoom, two finger rotate, and tapping on the "Add" button
		
		//queueEvent
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
}
