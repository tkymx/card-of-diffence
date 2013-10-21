package com.example.glsurfaceview;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

public class SurfaceRenderer implements Renderer {
	
	private Context c;
	private float rate;
	public static int widthRate, heightRate;
	
	// コンストラクタ
	public SurfaceRenderer( Context context )
	{
		c = context;
		
		widthRate = MainActivity.width / 100;
		heightRate = MainActivity.height / 100;
	}

	// 描画
	@Override
	public void onDrawFrame(GL10 gl) 
	{    
		gl.glClearColor(0.0f, 1.0f, 0.0f, 0.1f);
		//　表示画面とデプスバッファのクリア
		gl.glClear( GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT );
    	
        // ここにシーンの描画を呼ぶ
        SceneManager.Draw(gl);
	}
	
	// サーフェースのサイズが変更されたとき
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) 
	{
		// 画面比率
		rate = ( float )( width / height );
		
		// ビューポートの設定
		gl.glViewport( 0, 0, width, height );
		
		// テクスチャを使用する
		gl.glEnable(GL10.GL_TEXTURE_2D);
	}

	// サーフェイス生成
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	{		
		// 頂点配列を有効にする
    	gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
    	// テクスチャバッファの有効化
        gl.glEnableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
		
		// テクスチャを使用する
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		gl.glActiveTexture(GL10.GL_TEXTURE0);
        
        // 拡縮の設定
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
        
        // ブレンドの指定
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	Context GetContext()
	{
		return c;
	}
	
	public float getRate()
	{
		return rate;
	}
}
