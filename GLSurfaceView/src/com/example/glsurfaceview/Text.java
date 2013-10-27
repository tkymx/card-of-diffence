package com.example.glsurfaceview;

import com.example.glsurfaceview.Const.SpriteType;

public class Text extends Sprite {
	
	// コンストラクタ
	public Text()
	{
	}
	
	// 初期化
	public void Init( int left, int top, int Width, int Height, String text )
	{
		m_width = Width;
        m_height = Height;
		
		float[] vertexs = transToVertex(new Vector3( left, top, 0.0f ));
		
		vertexBuffer = new VertexBuffer( vertexs );
		texture = new Texture(Width, Height, text);      
		
		appear(SpriteType.TYPE_TEXT.getValue());
		
		bUse = true;
		bTextureSend = false;
	}
	
	// 生成
	public static Text Create( int left, int top, int Width, int Height, String text )
	{
		Text t = new Text();
		
		t.Init(left, top, Width, Height, text);
		
		return t;
	}
}
