package com.schneenet.cardstack;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	private CardView mGLSurfaceView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new CardView(this);
        this.setContentView(mGLSurfaceView);
    }
    
    public void onPause() {
    	super.onPause();
    	mGLSurfaceView.onPause();
    }
    
    public void onResume() {
    	super.onResume();
    	mGLSurfaceView.onResume();
    }
}