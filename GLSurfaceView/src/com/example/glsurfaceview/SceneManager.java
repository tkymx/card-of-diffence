package com.example.glsurfaceview;

import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

import com.example.scene.ClearScene;
import com.example.scene.GameOverScene;
import com.example.scene.GameScene;
import com.example.scene.TitleScene;

public class SceneManager {

	private static HashMap< String, Scene > Dictionary = new HashMap< String, Scene >();
	private static String mapKey;
	private static Scene scene;
	public static final String gameKey = "game"; 
	public static final String titleKey = "title"; 
	public static final String resultKey = "result"; 
	public static final String clearKey = "clear"; 
	public static final String gameoverKey = "gameover"; 
	private static SceneManager instance = new SceneManager();
	private boolean isCreate = false; 
	
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
		
		mapKey = titleKey;
		Dictionary.put( titleKey, new TitleScene() );
		Dictionary.put( gameKey, new GameScene() );
		Dictionary.put( clearKey, new ClearScene() );
		Dictionary.put( gameoverKey, new GameOverScene() );
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
		scene = Dictionary.get( mapKey );
		
		scene.Update();
	}
	
	// 描画処理 シンクロ
	synchronized public static void Draw( GL10 gl )
	{
		scene = Dictionary.get( mapKey );
		
     	// nullでないとき
     	if( scene != null )
     	{
     		scene.Draw( gl );
     	}
	}
	
	// シーンの切り替え
	public static void ChangeScene( String Key )
	{		
		scene = Dictionary.get( mapKey );
		
		// 後処理
		scene.Uninit();
		
		mapKey = Key;
		
		scene = Dictionary.get( mapKey );
		
		// 初期化処理
		scene.Init();
	}
	
	// シーンの取得
	public Scene GetScene()
	{
		scene = Dictionary.get( mapKey );
		
		return scene;
	}
}
