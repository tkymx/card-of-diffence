//////////////////////////////////////////////////////////////////
//	これはシーンのサンプルです。
//	新しいシーンを作る際はScene暮らすをimplementsして作ってください。
//	作成時はコンストラクタ、初期化、終了処理、更新処理、描画処理を記述してください。
//////////////////////////////////////////////////////////////////
package com.example.glsurfaceview;

import java.util.LinkedList;

import javax.microedition.khronos.opengles.GL10;

public class Game extends Scene {
	
	Sprite s;
	Sprite player;
	SpriteAnimation anime;
	Text t;
	
	// コンストラクタ
	public Game()
	{		
		// 親のコンストラクタを呼ぶ
		super();
		
		// スプライトの追加
		s = Sprite.Create( 0, 0, 500, 500, R.drawable.image4, Const.SpriteType.TYPE_OTHER.getValue() );
		player = Sprite.Create( 500, 500, 100, 400, R.drawable.image1, Const.SpriteType.TYPE_OTHER.getValue() );
		anime = SpriteAnimation.Create(100, 100, 300, 300, 3, 1, 30, R.drawable.image4, Const.SpriteType.TYPE_BG.getValue());
//		t = Text.Create(300, 400, 100, 50, "DMTC2013");
	}
	
	@Override
	public void Init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void Uninit() {
		// TODO Auto-generated method stub
		Sprite.removeAll();
	}

	@Override
	public void Update() {
		
		super.Update();
		
		// TODO Auto-generated method stub
		
		for( int i = 0; i < Const.SpriteType.TYPE_MAX.getValue(); i++ )
		{
			LinkedList<Sprite> list = Sprite.spriteList.get(i);
			
			// リストに登録されているシーンの数分ループ
			for( int j = 0; j < list.size(); j++ )
			{
				// 使用中のとき
				if( list.get(j).GetUse() == true )
				{
					Sprite sp = list.get(j);					
					Vector3 vec = new Vector3( 1.0f, 0, 0 );
					s.Translate(vec);
				}
			}
		}
	}

	@Override
	public void Draw(GL10 gl) {
		
		super.Draw(gl);
		
	}
}
