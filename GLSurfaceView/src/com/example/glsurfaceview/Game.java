package com.example.glsurfaceview;

import java.util.LinkedList;

import javax.microedition.khronos.opengles.GL10;

public class Game implements Scene {
	
	Sprite s;
	Sprite player;
	// コンストラクタ
	public Game()
	{		
		// 親のコンストラクタを呼ぶ
		super();
		
		// スプライトの追加
		s = Sprite.Create( 0, 0, 600, 400, R.drawable.image1, Const.SpriteType.TYPE_OTHER.getValue() );
		player =Sprite.Create( 100, 100, 600, 400, R.drawable.ic_launcher, Const.SpriteType.TYPE_OTHER.getValue() );
		
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
					Vector3 vec = new Vector3( 1.0f, 0, 0 );
					s.Translate(vec);
					player.Translate(vec);
					
				}
			}
		}
	}

	@Override
	public void Draw(GL10 gl) {
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
					list.get(j).DrawSprite(gl);
				}
			}
		}
	}
}
