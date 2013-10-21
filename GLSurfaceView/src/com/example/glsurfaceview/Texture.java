package com.example.glsurfaceview;

import java.nio.FloatBuffer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Texture {
	
	private FloatBuffer texUV;
	private Bitmap texture;
	private int texBuffer[] = new int[1];
	
	// コンストラクタ
	public Texture( int id, float[] UV )
	{
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// テクスチャがつくられていないとき
		if( haveTextue == false )
		{
			// 画像の生成
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id );
			
			// テクスチャのUV座標の生成
			texUV = Common.FloatToBuffer( UV );
		}
		else
		{
			// テクスチャの取得
			Texture t = TextureManager.GetTexture(id);
			
			// 画像の取得
			texture = t.GetBitmap();
			
			// UVの取得
			texUV = t.GetUV();
		}
	}
	
	// コンストラクタ
	public Texture( int id )
	{
		// デフォルトUV
		float UV[] = {
			-1.0f, -1.0f,
			-1.0f, 0.0f,
			0.0f, -1.0f,
			0.0f, 0.0f,
		};
		
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// テクスチャがつくられていないとき
		if( haveTextue == false )
		{
			// 画像の生成
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id );
			
			// テクスチャのUV座標の生成
			texUV = Common.FloatToBuffer( UV );
		}
		else
		{
			// テクスチャの取得
			Texture t = TextureManager.GetTexture(id);
			
			// 画像の取得
			texture = t.GetBitmap();
			
			// UVの取得
			texUV = t.GetUV();
		}
	}
    
    // UV座標の取得
    public FloatBuffer GetUV()
    {
    	return texUV;
    }
    
    // 画像の取得
    public Bitmap GetBitmap()
    {
    	return texture;
    }
    
    // テクスチャ配列の取得
    public int[] GetTextureBuffer()
    {
    	return texBuffer;
    }

}
