package com.schneenet.cardstack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.MotionEvent;
import android.view.View;

public class CardView extends View {

	public CardView(Context context) {
		super(context);
		mPowerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, this.getClass().getName());
		
		//TODO Load last view card and last size ratio
		mCardSize = 1;
		loadCard("");
	}
	
	public void onPause() {
		mWakeLock.release();
	}
	
	public void onResume() {
		mWakeLock.acquire();
	}
	
	public void loadCard(String cid) {
		//TODO Load a card from Database
		//For now we are loading a blank
		mDisplayedCard = BitmapFactory.decodeResource(this.getResources(), R.drawable.card_base);
		
	}

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
		
		Matrix mMatrix = new Matrix();
		mMatrix.postRotate((float) mCardRotation);
		mMatrix.postTranslate(c.getWidth() / 2 - mCardWidth / 2, c.getHeight() / 2 - mCardHeight / 2);
		mMatrix.postScale(mCardSize, mCardSize);
		c.drawBitmap(mDisplayedCard, mMatrix, null);
		
		//TODO Add Button Resource
		//TODO Draw Add Button
	}
	
	@Override
	public boolean onTouchEvent(final MotionEvent e) {
		//TODO Handle swipe gestures and tapping on the "Add" button
		
		return true;
	}
	
}
