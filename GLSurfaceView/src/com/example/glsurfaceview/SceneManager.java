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
	
	// �R���X�g���N�^
	private SceneManager()
	{
		// ���Ă��č쐬�����Ȃ�
		if( !isCreate )
		{
			// ��Ƀ��X�g�𐶐�
			Sprite.ListCreate();
			
			isCreate = true;
		}
		
		mapKey = titleKey;
		Dictionary.put( titleKey, new TitleScene() );
		Dictionary.put( gameKey, new GameScene() );
		Dictionary.put( clearKey, new ClearScene() );
		Dictionary.put( gameoverKey, new GameOverScene() );
	}
	
	// ����������
	public void Init()
	{
	}
	
	// �㏈��
	public void Uninit()
	{
	}
	
	// �X�V���� �V���N��
	synchronized public void Update()
	{
		scene = Dictionary.get( mapKey );
		
		scene.Update();
	}
	
	// �`�揈�� �V���N��
	synchronized public static void Draw( GL10 gl )
	{
		scene = Dictionary.get( mapKey );
		
     	// null�łȂ��Ƃ�
     	if( scene != null )
     	{
     		scene.Draw( gl );
     	}
	}
	
	// �V�[���̐؂�ւ�
	public static void ChangeScene( String Key )
	{		
		scene = Dictionary.get( mapKey );
		
		// �㏈��
		scene.Uninit();
		
		mapKey = Key;
		
		scene = Dictionary.get( mapKey );
		
		// ����������
		scene.Init();
	}
	
	// �V�[���̎擾
	public Scene GetScene()
	{
		scene = Dictionary.get( mapKey );
		
		return scene;
	}
}
