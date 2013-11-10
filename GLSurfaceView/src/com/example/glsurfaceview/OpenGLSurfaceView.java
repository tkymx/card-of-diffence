package com.example.glsurfaceview;

import com.example.select.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.provider.MediaStore;
import android.view.MotionEvent;

public class OpenGLSurfaceView extends GLSurfaceView{
	
	private SurfaceRenderer renderer;
	private SceneManager sceneManager;
	private Thread thread;
	public static Context c;

	public OpenGLSurfaceView(Context context) 
	{
		super(context);
		// TODO Auto-generated constructor stub
		
		c = context;
		
		// レンダラーの生成
		renderer = new SurfaceRenderer( context );
		
		// レンダラーの設定
		setRenderer( renderer );
		
		// シーンマネージャーの生成
		sceneManager = SceneManager.getInstance();
		
		thread = new Thread( new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 更新スレッド
				while( thread != null )
				{
					Scene scene = sceneManager.GetScene();
					
					// NULLのとき
					if( scene!= null ) {
					
						// 更新処理
						scene.Update();											

						//入力の初期化
						Touch touch = Touch.getInstance();
						touch.UpdateEnd();
					}
					
					// 例外処理
					try
					{
						// 60FPSで更新
						Thread.sleep( 1000 / 60 );
					}
					catch(Exception e)
					{
					}
				}	
			}
		} );
		
		thread.start();
	}

	// タッチイベントの処理
	@Override
	public boolean onTouchEvent( MotionEvent event )
	{
		//Intent intent = new Intent();
		//intent.setClass(c, com.example.glsurfaceview.DeckSelect.class);
		//intent.setClassName("com.example.select", "com.example.glsurfaceview.DeckSelect");
		//c.startActivity(intent);

		Touch touch = Touch.getInstance();
		
		touch.Update(event);
		
		return true;
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
	}
	  
	@Override
	public void onPause()
	{
		super.onPause();
	}
}
