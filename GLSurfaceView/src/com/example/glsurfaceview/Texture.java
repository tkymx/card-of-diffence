package com.example.glsurfaceview;

import java.nio.FloatBuffer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Texture {
	
	private FloatBuffer texUV;
	private Bitmap texture;
	private int texBuffer[] = new int[1];
	private static BitmapFactory.Options options = new BitmapFactory.Options();
	
	// コンストラクタ
	public Texture( int id, float[] UV )
	{
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// テクスチャがつくられていないとき
		if( haveTextue == false )
		{			
			options.inScaled = false;
			
			// 画像の生成
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id, options );
			
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
			1.0f, 1.0f,
			1.0f, 0.0f,
			0.0f, 1.0f,
			0.0f, 0.0f,
		};
		
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// テクスチャがつくられていないとき
		if( haveTextue == false )
		{
			options.inScaled = false;
			
			// 画像の生成
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id, options );
			
			// テクスチャのUV座標の生成
			texUV = Common.FloatToBuffer( UV );
			
			// テクスチャをリストに追加
			TextureManager.AddTexture(id, this);
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
	
	public Texture( int width, int height, String text )
	{
		texture = Bitmap.createBitmap(256, 256, Config.ARGB_8888);
		
		Canvas canvas = new Canvas( texture );
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.FILL);
        canvas.drawColor(0);
        canvas.drawText(text, 0, 15, paint); 
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
