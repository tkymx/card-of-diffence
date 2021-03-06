package com.example.glsurfaceview;

import com.example.data.DataBase;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class OpenGLSurfaceView extends GLSurfaceView{
	
	private SurfaceRenderer renderer;
	private SceneManager sceneManager;
	private Thread thread;
	public static Context c;

	//ストップフラグの設定
	private static boolean stopflag;
	public static void GameStop(){stopflag = true;};
	public static void GameStart(){stopflag = false;};
	public static boolean IsGameStop(){return stopflag;};	
	
	private boolean isHalt = false;	

	public OpenGLSurfaceView(Context context) 
	{
		super(context);
		// TODO Auto-generated constructor stub
		
		c = context;
		
		// レンダラーの生成
		renderer = new SurfaceRenderer( context );
		
		// レンダラーの設定
		setRenderer( renderer );
		
		// BGMの作成
		BGMSound.getInstance().loadBGM();
		
		//データベースの初期化処理などを行う
		DataBase.Init();
		DataBase.LoadData();		
		
		// シーンマネージャーの生成
		sceneManager = SceneManager.getInstance();
		
		// スレッド停止フラグ
		isHalt = false;		
		
		thread = new Thread( new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 更新スレッド	
				while( thread != null )
				{
					if( !isHalt )
					{
						Scene scene = sceneManager.GetScene();
						
						// NULLのとき
						if( scene!= null ) {		
							
							scene.Update();											
							
							//入力の初期化
							Touch touch = Touch.getInstance();
							touch.UpdateEnd();
							
							// BGM再生監視
							BGMSound.getInstance().playWatching();
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
		isHalt = false;
		BGMSound.getInstance().replayAll();
	}
	  
	@Override
	public void onPause()
	{
		isHalt = true;
		thread.interrupt();
		BGMSound.getInstance().stopAll();
	}	
}
