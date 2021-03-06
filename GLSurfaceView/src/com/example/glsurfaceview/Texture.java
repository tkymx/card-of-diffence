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
	private float texColor[] = new float[4];
	private Bitmap texture;
	private int texBuffer[] = new int[1];
	private static BitmapFactory.Options options = new BitmapFactory.Options();
	private boolean isBind;
	private int ID;
	
	// コンストラクタ
	public Texture( int id, float[] UV )
	{
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// テクスチャがつくられていないとき
		if( haveTextue == false )
		{			
			options.inScaled = false;
			
			float color[] = { 1.0f, 1.0f, 1.0f, 1.0f };
			
			// 画像の生成
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id, options );
			
			// 色の設定
			texColor = color;
			
			// テクスチャをリストに追加
			TextureManager.AddTexture(id, this);
			
			// IDの保存
			ID = id;
			
			isBind = false;
		}
		else
		{
			// テクスチャの取得
			Texture t = TextureManager.GetTexture(id);
			
			// 画像の取得
			texture = t.GetBitmap();
			
			// 色の設定
			texColor = t.GetColor();
			
			// テクスチャバッファの取得
			texBuffer = t.GetTextureBuffer();
			
			// IDの取得
			ID = t.GetID();
			
			isBind = true;
		}
		
		// テクスチャのUV座標の生成
		texUV = Common.FloatToBuffer( UV );
	}
	
	// コンストラクタ
	public Texture( int id )
	{
		// デフォルトUV
		float UV[] = {
			0.0f, 1.0f,
			0.0f, 0.0f,
			1.0f, 1.0f,
			1.0f, 0.0f,
		};
		
		float color[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// テクスチャがつくられていないとき
		if( haveTextue == false )
		{
			options.inScaled = false;
			
			// 画像の生成
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id, options );
			
			// テクスチャのUV座標の生成
			texUV = Common.FloatToBuffer( UV );
			
			// 色の設定
			texColor = color;
			
			// テクスチャをリストに追加
			TextureManager.AddTexture(id, this);
			
			// IDの保存
			ID = id;
		}
		else
		{
			// テクスチャの取得
			Texture t = TextureManager.GetTexture(id);
			
			// 画像の取得
			texture = t.GetBitmap();
			
			// UVの取得
			texUV = t.GetUV();
			
			// 色の設定
			texColor = t.GetColor();
			
			// IDの取得
			ID = t.GetID();
		}
		
		isBind = false;
	}
	
	public Texture( int width, int height, String text, float[] color )
	{
		texture = Bitmap.createBitmap(256, 16, Config.ARGB_8888);
		
		Canvas canvas = new Canvas( texture );
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.FILL);
        canvas.drawColor(0);
        canvas.drawText(text, 0, 15, paint); 
        
        // デフォルトUV
 		float UV[] = {
 			0.0f, 1.0f,
 			0.0f, 0.0f,
 			1.0f, 1.0f,
 			1.0f, 0.0f,
 		};
 		
 		// テクスチャのUV座標の生成
 		texUV = Common.FloatToBuffer( UV );
 		
 		// 色の設定
 		texColor = color;
	}
	
	// UV座標のセット
	public void SetUV( float[] UV )
	{
		texUV = Common.FloatToBuffer(UV);
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
    
    // テクスチャのセット
    public void SetTextureBuffer( int tex )
    {
    	texBuffer[0] = tex;
    }

    // バインド状況取得
    public boolean GetBind()
    {
    	return isBind;
    }
    
    // バインドを有効にする
    public void SetBind( boolean bind )
    {
    	isBind = bind;
    }
    
    // 色の取得
    public float[] GetColor()
    {
    	float color[] = texColor.clone();
    	return color;
    }
    
    // 色の設定
    public void SetColor( float r, float g, float b, float a )
    {
    	float color[] = { r, g, b, a };
    	
    	texColor = color;
    }
    
    // 再読み込み
    public void Resume()
    {
    	options.inScaled = false;
		isBind = false;
		
		// 画像の生成
		texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), ID, options );
    }

    
    // IDの取得
    public int GetID()
    {
    	return ID;
    }
    
    // IDのセット
    public void SetID( int id )
    {
    	boolean haveTextue = TextureManager.haveTexture(id);
		
		// テクスチャがつくられていないとき
		if( haveTextue == false )
		{			
			options.inScaled = false;
			
			float color[] = { 1.0f, 1.0f, 1.0f, 1.0f };
			
			// 画像の生成
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id, options );
			
			// 色の設定
			texColor = color;
			
			// テクスチャをリストに追加
			TextureManager.AddTexture(id, this);
			
			// IDの保存
			ID = id;
			
			isBind = false;
		}
		else
		{
			// テクスチャの取得
			Texture t = TextureManager.GetTexture(id);
			
			// 画像の取得
			texture = t.GetBitmap();
			
			// テクスチャバッファの取得
			texBuffer = t.GetTextureBuffer();
			
			// IDの取得
			ID = t.GetID();
			
			isBind = true;
		}
    }
}
