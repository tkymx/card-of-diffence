package com.example.glsurfaceview;

import java.util.HashMap;
import java.util.LinkedList;

public class TextureManager {
	
	public static HashMap< String, Texture > Dictionary = new HashMap< String, Texture >();
	public static LinkedList< String > IDList = new LinkedList< String >();

	// コンストラクタ
	public TextureManager()
	{
	}
	
	// テクスチャの登録
	public static void AddTexture( int id, Texture texture )
	{
		String s = String.valueOf( id );
		
		Dictionary.put( s, texture );
		IDList.add( s );
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
	
	// テクスチャの再読み込み
	public static void ResumeAll()
	{
		Texture texture;
		
		for( int i = 0; i < IDList.size(); i++ )
		{
			// 画像の再読み込み
			texture = Dictionary.get( IDList.get(i) );
			
			texture.Resume();
		}
		
		Number.getInstance().Resume();
	}
}
