package com.example.glsurfaceview;

import java.util.HashMap;

public class TextureManager {
	
	public static HashMap< String, Texture > Dictionary = new HashMap< String, Texture >();

	// コンストラクタ
	public TextureManager()
	{
	}
	
	// テクスチャの登録
	public static void AddTexture( int id, Texture texture )
	{
		String s = String.valueOf( id );
		
		Dictionary.put( s, texture );
	}
	
	// テクスチャがすでにあるかの確認
	public static boolean haveTexture( int id )
	{
		String s = String.valueOf( id );
		
		// nullのとき
		if( Dictionary.get( s ) == null )
		{
			return false;
		}
		
		return true;
	}
	
	// テクスチャの取得
	public static Texture GetTexture( int id )
	{
		String s = String.valueOf( id );
		
		Texture texture = Dictionary.get(s);
		
		return texture;
	}
}
