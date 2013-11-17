package com.example.glsurfaceview;

import javax.microedition.khronos.opengles.GL10;

import com.example.scene.ClearScene;
import com.example.scene.GameOverScene;
import com.example.scene.GameScene;
import com.example.scene.TitleScene;

public class SceneManager {

	private static SceneManager instance = new SceneManager();
	private boolean isCreate = false; 
	private static Scene onScene;

	public static final int titleKey = 0;
	public static final int gameKey = 1;
	public static final int resultKey = 2;
	public static final int clearKey = 3;
	public static final int gameoverKey = 4;
	
	public static SceneManager getInstance()
	{
		return instance;
	}
	
	// コンストラクタ
	private SceneManager()
	{
		// 閉じても再作成させない
		if( !isCreate )
		{
			// 先にリストを生成
			Sprite.ListCreate();
			
			isCreate = true;
		}
		
		onScene = new TitleScene();
		
		onScene.Init();
	}
	
	// 初期化処理
	public void Init()
	{
	}
	
	// 後処理
	public void Uninit()
	{
	}
	
	// 更新処理 シンクロ
	synchronized public void Update()
	{
		onScene.Update();
	}
	
	// 描画処理 シンクロ
	synchronized public static void Draw( GL10 gl )
	{
     	// nullでないとき
     	if( onScene != null )
     	{
     		onScene.Draw( gl );
     	}
	}
	
	// シーンの切り替え
	public static void ChangeScene( int Key )
	{		
		// 後処理
		onScene.Uninit();
		Sprite.removeAll();
		
		switch( Key )
		{
		case titleKey:
			onScene = new TitleScene();
			
			break;
			
		case gameKey:
			onScene = new GameScene();
			
			break;
			
		case gameoverKey:
			onScene = new GameOverScene();
			
			break;
			
		case clearKey:
			onScene = new ClearScene();
			
			break;
		}
		
		// 初期化
		onScene.Init();
	}
	
	// シーンの取得
	public Scene GetScene()
	{
		return onScene;
	}
}
