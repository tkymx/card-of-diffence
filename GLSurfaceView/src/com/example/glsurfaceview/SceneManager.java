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
		
		onScene = new TitleScene();
		
		onScene.Init();
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
		onScene.Update();
	}
	
	// �`�揈�� �V���N��
	synchronized public static void Draw( GL10 gl )
	{
     	// null�łȂ��Ƃ�
     	if( onScene != null )
     	{
     		onScene.Draw( gl );
     	}
	}
	
	// �V�[���̐؂�ւ�
	public static void ChangeScene( int Key )
	{		
		// �㏈��
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
		
		// ������
		onScene.Init();
	}
	
	// �V�[���̎擾
	public Scene GetScene()
	{
		return onScene;
	}
}
