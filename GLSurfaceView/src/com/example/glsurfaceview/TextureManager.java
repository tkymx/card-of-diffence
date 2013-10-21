package com.example.glsurfaceview;

import java.util.HashMap;

public class TextureManager {
	
	public static HashMap< String, Texture > Dictionary = new HashMap< String, Texture >();

	// �R���X�g���N�^
	public TextureManager()
	{
	}
	
	// �e�N�X�`���̓o�^
	public static void AddTexture( int id, Texture texture )
	{
		String s = String.valueOf( id );
		
		Dictionary.put( s, texture );
	}
	
	// �e�N�X�`�������łɂ��邩�̊m�F
	public static boolean haveTexture( int id )
	{
		String s = String.valueOf( id );
		
		// null�̂Ƃ�
		if( Dictionary.get( s ) == null )
		{
			return false;
		}
		
		return true;
	}
	
	// �e�N�X�`���̎擾
	public static Texture GetTexture( int id )
	{
		String s = String.valueOf( id );
		
		Texture texture = Dictionary.get(s);
		
		return texture;
	}
}
