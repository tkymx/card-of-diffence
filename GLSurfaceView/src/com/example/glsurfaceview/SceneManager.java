package com.example.glsurfaceview;

import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

public class SceneManager {

	private static HashMap< String, Scene > Dictionary = new HashMap< String, Scene >();
	private static String mapKey;
	private static Scene scene;
	public static final String gameKey = "game"; 
	public static final String titleKey = "title"; 
	public static final String resultKey = "result"; 
	
	// �R���X�g���N�^
	public SceneManager()
	{
		// ��Ƀ��X�g�𐶐�
		Sprite.ListCreate();
		
		Scene game = new Game();
		
		mapKey = gameKey;
		Dictionary.put( mapKey, game );
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
	public void ChangeScene( String Key )
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
	public static Scene GetScene()
	{
		scene = Dictionary.get( mapKey );
		
		return scene;
	}
}
