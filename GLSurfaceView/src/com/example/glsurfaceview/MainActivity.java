package com.example.glsurfaceview;

import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.graphics.Point;

public class MainActivity extends Activity {
	
	OpenGLSurfaceView surface;
	public static int width, height;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		WindowManager windowmanager = (WindowManager)getSystemService(WINDOW_SERVICE);
		Point point = new Point(); 
		Display disp = windowmanager.getDefaultDisplay(); 
		
		disp.getSize(point);
		
		width = point.x;
		height = point.y;
		
		surface = new OpenGLSurfaceView(this);
		setContentView(surface);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		surface.onResume();
	}
	  
	@Override
	protected void onPause()
	{
		super.onPause();
		surface.onPause();
	}

}
