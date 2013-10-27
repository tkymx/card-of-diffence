//////////////////////////////////////////////////////////////////
//	生成するときに画像を何分割するかを決めてください
//	例) 横:3	縦:1	みたいな形です。
//	あとはなんフレームで画像が更新されるかの時間を指定してください
//	例)	時間:30	30フレームで更新
//	こいつを継承したクラスを作るときはUpdateにこいつのUpdateを読んでください
//////////////////////////////////////////////////////////////////
package com.example.glsurfaceview;

public class SpriteAnimation extends Sprite {
	
	private float animeUVX, animeUVY;
	private float[] animeUV = new float[2];
	private int animeTime, animeNowTime;
	private float[] UV = new float[8];
	
	// コンストラクタ
	public SpriteAnimation()
	{
	}
	
	// 生成
	public static SpriteAnimation Create( float left, float top, float width, float height, int UVWidth, int UVHeight, int animeTime, int id, int SpriteType )
	{
		SpriteAnimation s = new SpriteAnimation();
		
		s.Init( left, top, width, height, UVWidth, UVHeight, animeTime, id, SpriteType );
		
		return s;
	}
	
	// 初期化処理
	public void Init( float left, float top, float width, float height, int UVWidth, int UVHeight, int animeTime, int id, int SpriteType )
	{
		super.Init(left, top, width, height, id, SpriteType);
		
		this.animeTime = animeTime;
		
		animeUV[0] = 0.0f;
		animeUV[1] = 0.0f;
		animeUVX = 1.0f / ( float )UVWidth;
		animeUVY = 1.0f / ( float )UVHeight;
		
		makeUV();
		
		texture.SetUV(UV);
	}
	
	// 更新処理
	@Override
	public boolean Update()
	{
		// アニメーションの時間が一定時間たったとき
		if( ( animeNowTime % animeTime ) == 0 )
		{
			// テクスチャの更新
			animeUV[0] += animeUVX;
			animeUV[1] += animeUVY;
			
			// アニメーションがテクスチャ最大を超えたとき
			if( animeUV[0] >= 1.0f )
			{
				animeUV[0] = 0.0f;
			}
			
			// アニメーションがテクスチャ最大を超えたとき
			if( animeUV[1] >= 1.0f )
			{
				animeUV[1] = 0.0f;
			}
			
			makeUV();
			
			// UVのセット
			texture.SetUV( UV );
		}
		
		// 時間のインクリメント
		animeNowTime++;
		
		return true;
	}
	
	// アニメーションの座標を返す
	public float[] getAnime()
	{
		return animeUV;
	}
	
	// UVの横幅の取得
	public float getAnimeWidth()
	{
		return animeUVX;
	}
	
	// UVの縦幅の取得
	public float getAnimeHiehgt()
	{
		return animeUVY;
	}
	
	// UV座標の生成
	private void makeUV( )
	{
		UV[0] =	animeUV[0];
		UV[1] = animeUV[1];
		UV[2] = animeUV[0];
		UV[3] = animeUV[1] + animeUVY;
		UV[4] = animeUV[0] + animeUVX;
		UV[5] = animeUV[1];
		UV[6] = animeUV[0] + animeUVX;
		UV[7] = animeUV[1] + animeUVY;
	}
	
}
