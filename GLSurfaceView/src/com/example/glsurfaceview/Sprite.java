package com.example.glsurfaceview;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

public class Sprite {
	
	// TODO リストからはずす
	public static ArrayList<LinkedList<Sprite>> spriteList = new ArrayList<LinkedList<Sprite>>();
	public static final int maxSpriteListNum = Const.SpriteType.TYPE_MAX.getValue();
	
	protected VertexBuffer vertexBuffer;
	protected Texture texture;
	private Vector3 trans = new Vector3();
	private Vector3 rot = new Vector3();
	private Vector3 scl = new Vector3( 1.0f, 1.0f, 1.0f );
	protected boolean bUse;
	protected boolean bTextureSend;
	protected float m_width, m_height;
	protected int spriteType = Const.SpriteType.TYPE_OTHER.getValue();
	private static boolean isCreate = false;
		
	// コンストラクタ
	public Sprite()
	{
	}
	
	// 生成
	public static Sprite Create( float[] vertex, Vector3 pos, int id, int SpriteType )
	{
		Sprite s = new Sprite();
		
		// 初期化
		s.Init( vertex, id, SpriteType );
		s.setTrans( pos );
		
		return s;
	}
	
	// 生成
	public static Sprite Create( float[] vertex, int id, float[] UV, int SpriteType )
	{
		Sprite s = new Sprite();
		
		// 初期化
		s.Init( vertex, id, UV, SpriteType );
		
		return s;
	}
	
	// 生成
	public static Sprite Create( float[] vertex, int id, int SpriteType )
	{
		Sprite s = new Sprite();
		
		// 初期化
		s.Init( vertex, id, SpriteType );
		
		return s;
	}

	// 生成 これ推奨
	public static Sprite Create( float left, float top, float width, float height, int id, int SpriteType )
	{
		Sprite s = new Sprite();
		
		// 初期化
		s.Init( left, top, width, height, id, SpriteType );
		
		return s;
	}
	
	// 初期化処理
	public void Init()
	{
	}
	
	// 初期化処理
	public void Init( float[] vertex, int id, float[] UV, int SpriteType )
	{
		vertexBuffer = new VertexBuffer( vertex );
		texture = new Texture( id, UV );
		appear( SpriteType );
		
		bUse = true;
		bTextureSend = false;
	}
	
	// 初期化処理
	public void Init( float[] vertex, int id, int SpriteType )
	{
		vertexBuffer = new VertexBuffer( vertex );
		texture = new Texture( id );
		appear( SpriteType );
		
		bUse = true;
		bTextureSend = false;
	}
	
	// 初期化処理
	public void Init( float left, float top, float width, float height, int id, int SpriteType )
	{
		// 幅の格納
		m_width = width;
		m_height = height;
				
		float[] vertexs = transToVertex(new Vector3( left, top, 0.0f ));
		
		vertexBuffer = new VertexBuffer( vertexs );
		texture = new Texture( id );
		appear( SpriteType );
		
		bUse = true;
		bTextureSend = false;
	}
	
	// 後処理
	public void Uninit()
	{
	}
	
	// 更新処理
	public boolean Update()
	{
		return true;
	}
	
	// スプライトの設定
    public void SetSprite( GL10 gl )
    {
    	// テクスチャをopenglへ送っていないとき
    	if( bTextureSend != true )
    	{
	    	// テクスチャバッファの取得
	    	Texture texture = GetTexture();
	    	
	    	int tex[] = texture.GetTextureBuffer();
	    	Bitmap bitmap = texture.GetBitmap();
	    		    	
	    	// 画像の読み込み
	    	gl.glGenTextures( 1, tex, 0 );
	    	
	    	// 拡縮の設定
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
	    	
	    	// テクスチャのバインド
	    	gl.glBindTexture( GL10.GL_TEXTURE_2D, tex[0] );
	    		    	
	    	// サンプラーにテクスチャを送る
	        GLUtils.texImage2D( GL10.GL_TEXTURE_2D, 0, bitmap, 0 );
	        
	        //bitmap.recycle();
	        
	        bTextureSend = true;
    	}
    }
    
    // スプライトの描画
    public void DrawSprite( GL10 gl )
    {
    	Texture texture = GetTexture();
    	FloatBuffer buffer = vertexBuffer.GetVertexBuffer();
    	FloatBuffer UV = texture.GetUV();
    	int size = vertexBuffer.GetVertexBufferSize();
    	int tex[] = texture.GetTextureBuffer();
    	
    	// 頂点配列を有効にする
    	gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
    	
    	// テクスチャを有効にする
    	gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    	
    	//gl.glActiveTexture(GL10.GL_TEXTURE0);
    	
    	// デプスバッファのテストを無効にする
     	gl.glDisable(GL10.GL_DEPTH_TEST);
     	
     	// ライトを無効にする
     	gl.glDisable(GL10.GL_LIGHTING);
     	
     	// テクスチャを有効にする
     	gl.glEnable(GL10.GL_TEXTURE_2D);
     	
     	// 1度だけ画像をopenglへ転送する
     	SetSprite(gl);
    	
    	// テクスチャのバインド
    	gl.glBindTexture( GL10.GL_TEXTURE_2D, tex[0] );

        // UV配列をGLに紐づけ
        gl.glTexCoordPointer( 2, GL10.GL_FLOAT, 0, UV );
        
        // 頂点配列のセット
     	gl.glVertexPointer( 3, GL10.GL_FLOAT, 0, buffer );
		
		// 描画
		gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 0, size );
    }
	
	// 頂点バッファの取得
	public VertexBuffer GetVertexBuffer()
	{
		return  vertexBuffer;
	}
	
	// テクスチャの取得
	public Texture GetTexture()
	{
		return texture;
	}

	// 座標の取得
	public Vector3 getTrans()
	{
		return trans;
	}
	
	protected float[] transToVertex( Vector3 trans )
	{
		this.trans = trans;
		
		float l, t, w, h;
		
		l = ( this.trans.x * 2.0f ) - ( float )MainActivity.width;
		t = ( this.trans.y * 2.0f ) - ( float )MainActivity.height;
		
		l = ( float )l / (float )MainActivity.width;
		t = ( float )t / ( float )MainActivity.height;
		w = l + ( float )m_width / ( float )MainActivity.width;
		h = t + ( float )m_height / ( float )MainActivity.height;
		
		float[] vertexs ={
				l, t, 0.0f,
				l, h, 0.0f,
				w, t, 0.0f,
				w, h, 0.0f
		};
		
		return vertexs;
	}

	// 座標のセット
	public void setTrans( Vector3 trans )
	{
		this.trans = trans;
		
		float[] vertexs = transToVertex(trans);
		
		vertexBuffer.SetVertexBuffer(vertexs);
	}
	
	
	// 座標の増加
	public void Translate( Vector3 trans )
	{
		this.trans.Add(trans);
		
		float[] vertexs = transToVertex(this.trans);
		
		vertexBuffer.SetVertexBuffer(vertexs);
	}

	// 角度の取得
	public Vector3 getRot()
	{
		return rot;
	}

	// 角度のセット
	public void setRot( Vector3 rot )
	{
		this.rot = rot;
		
		float[] vertexs = rotToVertex(rot);
		
		vertexBuffer.SetVertexBuffer(vertexs);
	}
	
	// 角度の増加
	public void Rotation( Vector3 rot )
	{
		this.rot.Add(rot);
		
		float[] vertexs = rotToVertex(this.rot);
		
		vertexBuffer.SetVertexBuffer(vertexs);
	}
	
	// 回転
	protected float[] rotToVertex( Vector3 rot )
	{
		float[] work = transToVertex(trans);
		
		float[] vertexs = new float[12];
		
		vertexs[0] = (float) (work[0] * Math.cos( rot.x ) * Math.cos( rot.y )
					+ work[1] * ( Math.cos( rot.x ) * Math.sin( rot.y ) * Math.sin( rot.z ) - Math.sin( rot.y ) * Math.cos( rot.z ) ));
		vertexs[1] = (float) (work[0] * Math.sin( rot.x ) * Math.cos( rot.y )
					+ work[1] * ( Math.sin( rot.x ) * Math.sin( rot.y ) * Math.sin( rot.z ) + Math.cos( rot.x ) * Math.cos( rot.z ) ));
		vertexs[3] = vertexs[0];
		vertexs[4] = (float) (work[3] * Math.sin( rot.x ) * Math.cos( rot.y )
				+ work[4] * ( Math.sin( rot.x ) * Math.sin( rot.y ) * Math.sin( rot.z ) + Math.cos( rot.x ) * Math.cos( rot.z ) ));
		vertexs[6] = (float) (work[6] * Math.cos( rot.x ) * Math.cos( rot.y )
				+ work[7] * ( Math.cos( rot.x ) * Math.sin( rot.y ) * Math.sin( rot.z ) - Math.sin( rot.y ) * Math.cos( rot.z ) ));
		vertexs[7] = vertexs[1];
		vertexs[9] = vertexs[6];
		vertexs[10] = vertexs[4];
		
		return vertexs;
	}

	// スケールの取得
	public Vector3 getScl()
	{
		return scl;
	}

	// スケールのセット
	public void setScl( Vector3 scl )
	{
		this.scl = scl;
	}
	
	// 使用中かの判定
	public boolean GetUse()
	{
		return bUse;
	}
	
	// 横幅の取得
	public float GetWidth()
	{
		return m_width;
	}
	
	// 縦幅の取得
	public float GetHeight()
	{
		return m_height;
	}
	
	// リストに追加
	public void appear( int spriteType )
	{
		// TODO ここがnullのまま作られてるのでエラーってる
		if( Sprite.spriteList != null )
		{
			LinkedList<Sprite> list = Sprite.spriteList.get(spriteType);
		
			// リストに自身を追加
			list.add(this);
		}
	}
	
	// リストからはずす
	public void remove()
	{
		// リストから自身を削除
		spriteList.get(spriteType).remove(this);
	}
	
	// リストからすべて外す
	public static void removeAll()
	{
		for( int i = 0; i < maxSpriteListNum; i++ )
		{
			for( int j = 0; j < spriteList.get(i).size(); j++ )
			{
				// リストからはずす
				spriteList.get(i).get(j).remove();
			}
		}
	}
	
	public static boolean ListCreate()
	{
		// まだ作られていないとき
		if( !isCreate )
		{
			for( int i = 0; i < Const.SpriteType.TYPE_MAX.getValue(); i++ )
			{
				// リストを作成
				Sprite.spriteList.add(i, new LinkedList<Sprite>());
			}
			
			isCreate = true;
		}
		
		return isCreate;
	}
}
