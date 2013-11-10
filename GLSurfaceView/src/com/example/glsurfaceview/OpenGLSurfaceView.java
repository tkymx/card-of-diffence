package com.example.glsurfaceview;

import co.example.data.DataBase;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class OpenGLSurfaceView extends GLSurfaceView{
	
	private SurfaceRenderer renderer;
	private SceneManager sceneManager;
	private Thread thread;
	public static Context c;

	private static boolean stopflag;
	public static void GameStop(){stopflag = true;};
	public static void GameStart(){stopflag = false;};

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
		
		//ストップフラグ
		GameStart();
		
		//データベースの初期化処理などを行う
		DataBase.Init();
		DataBase.LoadData();
		
		thread = new Thread( new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 更新スレッド
				while( thread != null )
				{
					if( !stopflag )
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
			}
		} );
		
		thread.start();
	}

	// タッチイベントの処理
	@Override
	public boolean onTouchEvent( MotionEvent event )
	{		
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
